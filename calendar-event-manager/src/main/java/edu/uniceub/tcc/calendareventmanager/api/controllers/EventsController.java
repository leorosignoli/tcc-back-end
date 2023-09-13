package edu.uniceub.tcc.calendareventmanager.api.controllers;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventCreateAllRequest;
import edu.uniceub.tcc.calendareventmanager.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.*;

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
  public void createEvents(@RequestBody final EventCreateAllRequest eventsRequest) {
    LOGGER.info("Creating events for owner {}", eventsRequest.eventOwner());
    eventService.createEvents(eventsRequest.data(), eventsRequest.eventOwner());
  }
}
