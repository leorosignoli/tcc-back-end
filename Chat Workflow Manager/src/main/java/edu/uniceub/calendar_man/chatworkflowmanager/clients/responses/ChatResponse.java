package edu.uniceub.calendar_man.chatworkflowmanager.clients.responses;

import java.util.List;

public class ChatResponse {

  public ChatResponse() {
  }

  public ChatResponse(List<Choice> choices) {
    this.choices = choices;
  }

  private List<Choice> choices;

  public static class Choice {

    private int index;
    private Message message;

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public Message getMessage() {
      return message;
    }

    public void setMessage(Message message) {
      this.message = message;
    }

    @Override
    public String toString() {
      return "Choice{" +
              "index=" + index +
              ", message=" + message +
              '}';
    }
  }

  public List<Choice> getChoices() {
    return choices;
  }

  public void setChoices(List<Choice> choices) {
    this.choices = choices;
  }

  @Override
  public String toString() {
    return "ChatResponse{" +
            "choices=" + choices +
            '}';
  }
}
