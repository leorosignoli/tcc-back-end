package edu.uniceub.calendar_man.chatworkflowmanager.configurations;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIRestTemplateConfig {

  private final String openaiApiKey;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(OpenAIRestTemplateConfig.class.getName());

  public OpenAIRestTemplateConfig(@Value("${openai.api.key}") final String openaiApiKey) {
    this.openaiApiKey = openaiApiKey;
  }

  @Bean
  @Qualifier("openaiRestTemplate")
  public RestTemplate openaiRestTemplate() {
    final RestTemplate restTemplate = new RestTemplate();
    restTemplate
        .getInterceptors()
        .add(
            (request, body, execution) -> {
                request.getHeaders().setBearerAuth(openaiApiKey);
              request.getHeaders().add("Content-Type", "application/json");
              final ClientHttpResponse response = execution.execute(request, body);
              logResponse(response);
              return response;
            });
    return restTemplate;
  }


  private void logResponse(final ClientHttpResponse response) {
    try {
      LOGGER.debug("Received response from OpenAI: {}", response.getBody());
    } catch (IOException e) {
      LOGGER.debug("Unable to decode response body. Error message: {}", e.getMessage());
    }
  }
}
