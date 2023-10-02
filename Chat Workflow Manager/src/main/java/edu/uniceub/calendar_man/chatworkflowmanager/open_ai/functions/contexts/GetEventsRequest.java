package edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class GetEventsRequest {

  @JsonPropertyDescription(
      "Date that will match all corresponding events. Format must be YYYY-MM-DD.")
  @JsonProperty(required = true)
  public String date;
}
