package edu.uniceub.calendar_man.chatworkflowmanager.controllers;


import edu.uniceub.calendar_man.chatworkflowmanager.controllers.request.MessageRequest;
import edu.uniceub.calendar_man.chatworkflowmanager.controllers.responses.ExchangePostResponse;
import org.bson.json.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {


    @PostMapping
    public ExchangePostResponse exchangeConversation(@RequestBody final MessageRequest request) {
        return null;
    }

}
