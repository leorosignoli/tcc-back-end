package edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions;

import com.theokanning.openai.completion.chat.ChatFunction;
import edu.uniceub.calendar_man.chatworkflowmanager.models.Event;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetCurrentDateResponse;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetEventsRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public final class FunctionUtils {

  private FunctionUtils() {}

  public static final List<String> ASSISTANT_ACTION_FUNCTIONS =
      List.of("get_calendar_context", "schedule_event", "get_current_date");

  public static ChatFunction getCalendarEventsFunction(
      final Function<GetEventsRequest, Object> getEvents) {
    return ChatFunction.builder()
        .name("get_calendar_context")
        .description("Get the user's calendar events for the given date.")
        .executor(GetEventsRequest.class, getEvents)
        .build();
  }

  public static ChatFunction scheduleNewEvent(final Function<Event, Object> scheduleEvent) {

    return ChatFunction.builder()
        .name("schedule_event")
        .description("Schedules a new event to the user's calendar.")
        .executor(Event.class, scheduleEvent)
        .build();
  }

  public static ChatFunction getCurrentDate() {
    return ChatFunction.builder()
        .name("get_current_date")
        .description("returns the current date.")
        .executor(
            Object.class,
            (empty) -> GetCurrentDateResponse.fromDate(LocalDateTime.now().toString()))
        .build();
  }
}
