package edu.uniceub.calendar_man.chatworkflowmanager.services;

import static edu.uniceub.calendar_man.chatworkflowmanager.constants.ApplicationConstants.OpenAiConstants.CHAT_GPT_SYSTEM_PROMPT;

import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.CalendarManagerClient;
import edu.uniceub.calendar_man.chatworkflowmanager.controllers.request.ConversationRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.Functions;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetEventsRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

  private final OpenAiService openAiService;

  private final CalendarManagerClient calendarManagerClient;

  public ExchangeService(
      final OpenAiService openAiService, final CalendarManagerClient calendarManagerClient) {
    this.openAiService = openAiService;
    this.calendarManagerClient = calendarManagerClient;
  }

  public ChatMessage exchangeMessages(final ConversationRequest request, final String user) {

    FunctionExecutor functionExecutor =
        new FunctionExecutor(List.of(Functions.getCalendarEventsFunction(getEvents(user))));

    final List<ChatMessage> messages = new ArrayList<>();

    messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), CHAT_GPT_SYSTEM_PROMPT));

    request
        .messages()
        .forEach(
            message ->
                messages.add(new ChatMessage(message.messageSource().value(), message.message())));

    final ChatCompletionRequest chatCompletionRequest =
        ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(messages)
            .functions(functionExecutor.getFunctions())
            .functionCall(new ChatCompletionRequest.ChatCompletionRequestFunctionCall("auto"))
            .maxTokens(256)
            .build();

    ChatMessage responseMessage =
        openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

    ChatFunctionCall functionCall =
        responseMessage
            .getFunctionCall();

    final ChatMessage functionResponseMessage =
        functionExecutor.executeAndConvertToMessageHandlingExceptions(functionCall);

    return responseMessage;
  }

  private Function<GetEventsRequest, Object> getEvents(final String user) {
    return request -> calendarManagerClient.getEvents(request.date, user);
  }
  ;
}
