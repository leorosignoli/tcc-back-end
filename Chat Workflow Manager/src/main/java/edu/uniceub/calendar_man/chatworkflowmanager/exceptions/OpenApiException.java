package edu.uniceub.calendar_man.chatworkflowmanager.exceptions;

public final class OpenApiException extends RuntimeException {

  private OpenApiException(final String message) {
    super(message);
  }

  public static void from(final String message) {
    throw new OpenApiException(message);
  }

  public static OpenApiException failedToDecodeJson() {
    return new OpenApiException("Failed to decode JSON");
  }
}
