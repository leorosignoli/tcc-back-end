package edu.uniceub.calendar_man.chatworkflowmanager.configurations;

import com.theokanning.openai.service.OpenAiService;
import edu.uniceub.calendar_man.chatworkflowmanager.properties.OpenAiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiServiceConfig {

  private final OpenAiProperties openAiProperties;

  public OpenAiServiceConfig(OpenAiProperties openAiProperties) {
    this.openAiProperties = openAiProperties;
  }

  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(openAiProperties.getApiKey());
  }
}
