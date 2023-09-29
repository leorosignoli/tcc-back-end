package edu.uniceub.calendar_man.chatworkflowmanager.controllers.responses;

import edu.uniceub.calendar_man.chatworkflowmanager.enums.GptCommand;

public record ExchangePostResponse(String message, GptCommand command) {
}
