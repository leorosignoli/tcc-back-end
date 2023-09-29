package edu.uniceub.calendar_man.chatworkflowmanager.controllers;

import edu.uniceub.calendar_man.chatworkflowmanager.controllers.request.MessageRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.services.ExchangeService;
import org.bson.json.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

  private final ExchangeService exchangeService;

  public ExchangeController(final ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  @PostMapping
  public JsonObject exchangeConversation(@RequestBody final MessageRequest request) {
    return exchangeService.exchangeConversation(request);
  }
}
