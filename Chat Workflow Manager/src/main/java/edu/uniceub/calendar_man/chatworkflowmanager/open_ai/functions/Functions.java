package edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions;

import com.theokanning.openai.completion.chat.ChatFunction;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetEventsRequest;
import java.util.function.Function;

public final class Functions {

  private Functions() {}

  public static ChatFunction getCalendarEventsFunction(
      Function<GetEventsRequest, Object> getEvents) {
    return ChatFunction.builder()
        .name("get_calendar_context")
        .description("Get the user's calendar events for the given date.")
        .executor(GetEventsRequest.class, getEvents)
        .build();
  }
}
