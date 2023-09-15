package edu.uniceub.tcc.calendareventmanager.api.controllers;

import static edu.uniceub.tcc.calendareventmanager.constants.LogConstants.Event.INFO_RECEIVED_EVENT_CREATION_REQUEST;
import static edu.uniceub.tcc.calendareventmanager.constants.LogConstants.Event.INFO_RECEIVED_GET_EVENTS_FOR_OWNER_REQUEST;
import static org.slf4j.LoggerFactory.*;

import edu.uniceub.tcc.calendareventmanager.api.dtos.request.EventCreateAllRequest;
import edu.uniceub.tcc.calendareventmanager.api.dtos.response.EventResponse;
import edu.uniceub.tcc.calendareventmanager.services.EventService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/events")
public class EventsController {

  private static final Logger LOGGER = getLogger(EventsController.class);
  private final EventService eventService;

  public EventsController(final EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<?> createEvents(@RequestBody final EventCreateAllRequest eventsRequest) {
    LOGGER.debug(INFO_RECEIVED_EVENT_CREATION_REQUEST, eventsRequest);
    final List<String> eventsCreated =
        eventService.createEvents(eventsRequest.data(), eventsRequest.eventOwner());

    if (eventsCreated.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No events created.");
    }
    final Map<String, List<String>> response = new HashMap<>();
    response.put("events", eventsCreated);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public List<EventResponse> getEvents(@RequestParam(name = "owner") final String eventOwner) {
    LOGGER.debug(INFO_RECEIVED_GET_EVENTS_FOR_OWNER_REQUEST, eventOwner);

    return eventService.getEventsForOwner(eventOwner);
  }
}
