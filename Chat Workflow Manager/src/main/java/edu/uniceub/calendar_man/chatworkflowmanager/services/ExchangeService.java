package edu.uniceub.calendar_man.chatworkflowmanager.services;

import static edu.uniceub.calendar_man.chatworkflowmanager.constants.ApplicationConstants.OpenAiConstants.CHAT_GPT_SYSTEM_PROMPT;
import static edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.FunctionUtils.ASSISTANT_ACTION_FUNCTIONS;

import com.google.common.collect.Iterables;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.CalendarManagerClient;
import edu.uniceub.calendar_man.chatworkflowmanager.models.Event;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.FunctionUtils;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetEventsRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.properties.OpenAiProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

  private final OpenAiService openAiService;

  private final CalendarManagerClient calendarManagerClient;

  private final OpenAiProperties openAiProperties;

  public ExchangeService(
      final OpenAiService openAiService,
      final CalendarManagerClient calendarManagerClient,
      final OpenAiProperties openAiProperties) {
    this.openAiService = openAiService;
    this.calendarManagerClient = calendarManagerClient;
    this.openAiProperties = openAiProperties;
  }

  public List<ChatMessage> exchangeMessages(
      final List<ChatMessage> requestMessages, final String user) {

    final FunctionExecutor functionExecutor = getFunctionExecutor(user);

    final List<ChatMessage> messages = new ArrayList<>();

    addSystemPrompt(requestMessages, messages);

    final ChatMessage responseMessage =
        openAiService
            .createChatCompletion(buildChatRequest(messages, functionExecutor, user))
            .getChoices()
            .get(0)
            .getMessage();

    messages.add(responseMessage);

    functionExecutor
        .executeAndConvertToMessageSafely(responseMessage.getFunctionCall())
        .ifPresent(messages::add);

    if (requiresAssistantAction(messages)) {
      return exchangeMessages(messages, user);
    }

    return messages;
  }

  private ChatCompletionRequest buildChatRequest(
      List<ChatMessage> messages, FunctionExecutor functionExecutor, String userId) {
    return ChatCompletionRequest.builder()
        .model(openAiProperties.getModel())
        .messages(messages)
        .functions(functionExecutor.getFunctions())
        .functionCall(new ChatCompletionRequest.ChatCompletionRequestFunctionCall("auto"))
        .maxTokens(256)
        .user(userId)
        .build();
  }

  @NotNull
  private FunctionExecutor getFunctionExecutor(String user) {
    return new FunctionExecutor(
        List.of(
            FunctionUtils.getCalendarEventsFunction(getEvents(user)),
            FunctionUtils.scheduleNewEvent(scheduleEvents(user)),
            FunctionUtils.getCurrentDate()));
  }

  private static void addSystemPrompt(
      final List<ChatMessage> requestMessages, final List<ChatMessage> messages) {
    requestMessages.stream()
        .findFirst()
        .ifPresent(
            message -> {
              if (!CHAT_GPT_SYSTEM_PROMPT.equals(message.getContent())) {
                messages.add(
                    new ChatMessage(ChatMessageRole.SYSTEM.value(), CHAT_GPT_SYSTEM_PROMPT));
              }
            });

    messages.addAll(requestMessages);
  }

  private static boolean requiresAssistantAction(List<ChatMessage> messages) {
    final var name = Iterables.getLast(messages).getName();
    if (StringUtils.isNotBlank(name)) {
      return ASSISTANT_ACTION_FUNCTIONS.contains(name);
    }
    return false;
  }

  private Function<GetEventsRequest, Object> getEvents(final String user) {
    return request -> calendarManagerClient.getEvents(request.date, user);
  }

  private Function<Event, Object> scheduleEvents(final String user) {
    return event -> calendarManagerClient.createEvent(event, user);
  }
}
