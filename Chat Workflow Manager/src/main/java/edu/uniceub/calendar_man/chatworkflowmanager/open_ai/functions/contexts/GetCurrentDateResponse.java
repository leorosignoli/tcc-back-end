package edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts;

import java.time.LocalDateTime;

public final class GetCurrentDateResponse {

  public final String currentDate;

  private GetCurrentDateResponse(final String currentDate) {
    this.currentDate = currentDate;
  }

  public static GetCurrentDateResponse fromDate(LocalDateTime date) {
    return new GetCurrentDateResponse(date.toString());
  }

  public static GetCurrentDateResponse fromDate(String date) {
    return new GetCurrentDateResponse(date);
  }
}
