package edu.uniceub.calendar_man.chatworkflowmanager.controllers;

import com.theokanning.openai.OpenAiHttpException;
import edu.uniceub.calendar_man.chatworkflowmanager.helpers.ErrorMessage;
import java.net.SocketTimeoutException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);

  @org.springframework.web.bind.annotation.ExceptionHandler({
    SocketTimeoutException.class,
    OpenAiHttpException.class
  })
  public ResponseEntity<ErrorMessage> handleUnavailableOpenAi(Exception exception) {
    LOGGER.error("Exception thrown by openAi: {}", exception.getMessage());
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(new ErrorMessage("Chat service is currently unavailable. Please try again later."));
  }
}
