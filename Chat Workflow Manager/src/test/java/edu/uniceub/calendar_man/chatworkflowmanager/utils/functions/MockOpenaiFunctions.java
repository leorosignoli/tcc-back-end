package edu.uniceub.calendar_man.chatworkflowmanager.utils.functions;

import edu.uniceub.calendar_man.chatworkflowmanager.models.Event;
import edu.uniceub.calendar_man.chatworkflowmanager.open_ai.functions.contexts.GetEventsRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import org.bson.json.JsonObject;

public final class MockOpenaiFunctions {

  private MockOpenaiFunctions() {}

  public static Function<GetEventsRequest, Object> getEventsMock() {
    return request ->
        List.of(
            new Event(
                "dqd9wjd",
                "Reuniao",
                LocalDateTime.now().plusHours(1).toString(),
                LocalDateTime.now().plusHours(2).toString()));
  }

  public static Function<Event, Object> scheduleEventsMock() {
    return request -> new JsonObject("{ \"message\": \"Event scheduled successfully!\"}");
  }
}
