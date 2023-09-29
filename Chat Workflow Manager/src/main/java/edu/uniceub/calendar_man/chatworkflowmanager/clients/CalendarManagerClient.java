package edu.uniceub.calendar_man.chatworkflowmanager.clients;

import edu.uniceub.calendar_man.chatworkflowmanager.models.Event;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${clients.calendar_manager.url}", value = "calendar-event-manager-client")
public interface CalendarManagerClient {

  @GetMapping("")
  public List<Event> getEvents(
      @RequestParam("startDate") String startDate, @RequestParam("owner") String eventOwner);
}
