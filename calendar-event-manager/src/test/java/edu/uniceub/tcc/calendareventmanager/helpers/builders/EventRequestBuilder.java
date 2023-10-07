package edu.uniceub.tcc.calendareventmanager.helpers.builders;

import edu.uniceub.tcc.calendareventmanager.api.dtos.request.EventRequest;
import java.util.UUID;

public class EventRequestBuilder {
  private String title;
  private String start;
  private String end;

  private String integrationId;

  public static EventRequest withDefaultValues() {
    return new EventRequestBuilder().defaultValues().build();
  }

  public EventRequestBuilder title(String title) {
    this.title = title;
    return this;
  }

  public EventRequestBuilder integrationId(String integrationId) {
    this.integrationId = integrationId;
    return this;
  }

  public EventRequestBuilder start(String start) {
    this.start = start;
    return this;
  }

  public EventRequestBuilder end(String end) {
    this.end = end;
    return this;
  }

  public EventRequestBuilder defaultValues() {
    this.title = "title";
    this.start = "2021-01-01T00:00:00";
    this.end = "2021-01-02T00:00:00";
    this.integrationId = UUID.randomUUID().toString();
    return this;
  }

  public EventRequest build() {
    return new EventRequest(title, start, end, integrationId);
  }
}
