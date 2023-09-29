package edu.uniceub.calendar_man.chatworkflowmanager.controllers;

import com.theokanning.openai.completion.chat.ChatMessage;
import edu.uniceub.calendar_man.chatworkflowmanager.controllers.request.ConversationRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.services.ExchangeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

  private final ExchangeService exchangeService;

  public ExchangeController(final ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  @PostMapping
  public ChatMessage exchangeConversation(
      @RequestBody final ConversationRequest data, @RequestHeader("userId") final String userId) {
    return exchangeService.exchangeMessages(data, userId);
  }
}
