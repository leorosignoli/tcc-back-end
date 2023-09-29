package edu.uniceub.calendar_man.chatworkflowmanager.services;

import static edu.uniceub.calendar_man.chatworkflowmanager.constants.ApplicationConstants.OpenAiConstants.CHAT_GPT_SYSTEM_PROMPT;
import static edu.uniceub.calendar_man.chatworkflowmanager.constants.ApplicationConstants.OpenAiConstants.USER_ROLE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.OpenAiClient;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.request.ChatRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.responses.ChatResponse;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.responses.Message;
import edu.uniceub.calendar_man.chatworkflowmanager.controllers.request.MessageRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.exceptions.OpenApiException;
import edu.uniceub.calendar_man.chatworkflowmanager.helpers.Monad;
import edu.uniceub.calendar_man.chatworkflowmanager.properties.OpenAiProperties;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
  private final OpenAiClient openAiClient;

  private final OpenAiProperties openAiProperties;
  private final ObjectMapper mapper;

  public ExchangeService(final OpenAiClient openAiClient, final OpenAiProperties openAiProperties) {
    this.openAiClient = openAiClient;
    this.openAiProperties = openAiProperties;
    mapper = new ObjectMapper();
  }

  public JsonObject exchangeConversation(final MessageRequest request) {

    return Monad.init(request.message())
        .applyFunction(this::chatRequestWithDefaultModel)
        .applyFunction(openAiClient::exchangeMessage)
        .applyFunction(this::responseToJson)
        .getValue();
  }

  private ChatRequest chatRequestWithDefaultModel(final String message) {
    return Monad.init(new ChatRequest(openAiProperties.getModel(), CHAT_GPT_SYSTEM_PROMPT))
        .applyConsumer(request -> request.getMessages().add(new Message(USER_ROLE, message)))
        .getValue();
  }

  private JsonObject responseToJson(ChatResponse response) {
    try {
      return new JsonObject(mapper.writeValueAsString(response));
    } catch (JsonProcessingException e) {
      throw OpenApiException.failedToDecodeJson();
    }
  }
}
