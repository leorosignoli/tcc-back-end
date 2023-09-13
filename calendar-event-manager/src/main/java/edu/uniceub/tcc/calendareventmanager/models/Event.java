package edu.uniceub.tcc.calendareventmanager.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {

  private String id;
  private String owner;

  private String title;

  private String startDate;

  private String endDate;

  public Event() {}

  public Event(String id, String owner, String title, String startDate, String endDate) {
    this.id = id;
    this.owner = owner;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
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
}
