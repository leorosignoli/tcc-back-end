package edu.uniceub.calendar_man.chatworkflowmanager.clients.request;

import edu.uniceub.calendar_man.chatworkflowmanager.clients.responses.Message;
import java.util.ArrayList;
import java.util.List;

public class ChatRequest {

  private String model;
  private List<Message> messages;
  private int n = 1;
  private double temperature;

  public ChatRequest(final String model, final String prompt) {
    this.model = model;

    this.messages = new ArrayList<>();
    this.messages.add(new Message("system", prompt));
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public int getN() {
    return n;
  }

  public void setN(int n) {
    this.n = n;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  @Override
  public String toString() {
    return "ChatRequest{"
        + "model='"
        + model
        + '\''
        + ", messages="
        + messages
        + ", n="
        + n
        + ", temperature="
        + temperature
        + '}';
  }
}
