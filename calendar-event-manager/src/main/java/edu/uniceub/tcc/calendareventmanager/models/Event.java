package edu.uniceub.tcc.calendareventmanager.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {

  private String id;
  private String owner;

  private String title;

  private String startDate;

  private String endDate;

  private String integrationId;

  private Boolean isDeleted = false;

  public Event() {}

  public Event(
      String id,
      String owner,
      String title,
      String startDate,
      String endDate,
      String integrationId,
      Boolean isDeleted) {
    this.id = id;
    this.owner = owner;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.integrationId = integrationId;
    this.isDeleted = isDeleted;
  }

  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Event withOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setIntegrationId(String integrationId) {
    this.integrationId = integrationId;
  }

  public String getIntegrationId() {
    return integrationId;
  }
}
