package edu.uniceub.tcc.calendareventmanager.helpers.builders;

import edu.uniceub.tcc.calendareventmanager.models.Event;

public final class EventBuilder {
  private String id;
  private String owner;
  private String title;
  private String startDate;
  private String endDate;
  private String integrationId;
  private Boolean isDeleted;

  private EventBuilder() {}

  public static EventBuilder anEvent() {
    return new EventBuilder();
  }

  public EventBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public EventBuilder withOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public EventBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public EventBuilder withStartDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  public EventBuilder withEndDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  public EventBuilder withIntegrationId(String integrationId) {
    this.integrationId = integrationId;
    return this;
  }

  public EventBuilder withIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
    return this;
  }

  public EventBuilder withDefaultValues() {
    this.id = null;
    this.owner = "231231";
    this.title = "title";
    this.startDate = "2021-06-01 00:00:00 +0000";
    this.endDate = "2021-06-02 00:00:00 +0000";
    this.integrationId = "integrationId";
    this.isDeleted = false;
    return this;
  }

  public Event build() {
    return new Event(id, owner, title, startDate, endDate, integrationId, isDeleted);
  }
}
