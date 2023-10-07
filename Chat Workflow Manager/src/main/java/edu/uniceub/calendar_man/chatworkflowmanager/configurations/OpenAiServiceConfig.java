package edu.uniceub.calendar_man.chatworkflowmanager.configurations;

import com.theokanning.openai.service.OpenAiService;
import edu.uniceub.calendar_man.chatworkflowmanager.properties.OpenAiProperties;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiServiceConfig {

  private final OpenAiProperties openAiProperties;

  private final Duration defaultRequestTimeOut = Duration.ofSeconds(50);

  public OpenAiServiceConfig(OpenAiProperties openAiProperties) {
    this.openAiProperties = openAiProperties;
  }

  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(openAiProperties.getApiKey(), defaultRequestTimeOut);
  }
}
