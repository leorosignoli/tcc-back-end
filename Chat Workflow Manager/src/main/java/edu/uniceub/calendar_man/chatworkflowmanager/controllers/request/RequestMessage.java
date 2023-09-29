package edu.uniceub.calendar_man.chatworkflowmanager.controllers.request;

import com.theokanning.openai.completion.chat.ChatMessageRole;

public record RequestMessage(String message, ChatMessageRole messageSource) {}
