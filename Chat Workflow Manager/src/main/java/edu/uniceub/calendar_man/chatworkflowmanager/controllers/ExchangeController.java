package edu.uniceub.calendar_man.chatworkflowmanager.controllers;

import com.theokanning.openai.completion.chat.ChatMessage;
import edu.uniceub.calendar_man.chatworkflowmanager.services.ExchangeService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

  private final ExchangeService exchangeService;

  public ExchangeController(final ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  @PostMapping("/user/{userId}")
  public List<ChatMessage> exchangeConversation(
      @RequestBody final List<ChatMessage> data, @PathVariable final String userId) {
    return exchangeService.exchangeMessages(data, userId);
  }
}
