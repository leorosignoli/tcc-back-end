package edu.uniceub.calendar_man.chatworkflowmanager.clients;

import edu.uniceub.calendar_man.chatworkflowmanager.clients.request.ChatRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.clients.responses.ChatResponse;
import edu.uniceub.calendar_man.chatworkflowmanager.properties.OpenAiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenAiClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(OpenAiClient.class.getName());

  private final RestTemplate restTemplate;

  private final OpenAiProperties openAiProperties;

  public OpenAiClient(
      @Qualifier("openaiRestTemplate") final RestTemplate restTemplate,
      final OpenAiProperties openAiProperties) {
    this.restTemplate = restTemplate;
    this.openAiProperties = openAiProperties;
  }

  public ChatResponse exchangeMessage(final ChatRequest request) {
    try {
        LOGGER.info("Sending request to OpenAI: {}", request);
      final ResponseEntity<ChatResponse> responseEntity =
          restTemplate.postForEntity(openAiProperties.getUrl(), request, ChatResponse.class);

      LOGGER.info("Received response code from OpenAI: {}", responseEntity.getStatusCode());
      LOGGER.info("Received response body from OpenAI: {}", responseEntity.getBody());

      return responseEntity.getBody();
    } catch (final HttpStatusCodeException e) {
      LOGGER.info("Received response code from OpenAI: {}", e.getStatusCode().value());
      LOGGER.debug("Received response body from OpenAI: {}", e.getResponseBodyAsString());
      return new ChatResponse();
    }
  }
}
