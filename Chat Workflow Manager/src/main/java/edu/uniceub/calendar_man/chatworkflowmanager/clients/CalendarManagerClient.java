package edu.uniceub.calendar_man.chatworkflowmanager.clients;

import edu.uniceub.calendar_man.chatworkflowmanager.clients.dtos.response.CreateEventResponse;
import edu.uniceub.calendar_man.chatworkflowmanager.models.Event;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${clients.calendar_manager.url}", value = "calendar-event-manager-client")
public interface CalendarManagerClient {

  @GetMapping("/events")
  List<Event> getEvents(
      @RequestParam("startDate") String startDate, @RequestParam("owner") String eventOwner);

  @PostMapping("/events/user/{userId}")
  CreateEventResponse createEvent(@RequestBody Event event, @PathVariable String userId);
}
