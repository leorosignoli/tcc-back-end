package edu.uniceub.calendar_man.chatworkflowmanager.controllers.request;

import java.util.List;

public record ConversationRequest(List<RequestMessage> messages, String conversationId) {}
