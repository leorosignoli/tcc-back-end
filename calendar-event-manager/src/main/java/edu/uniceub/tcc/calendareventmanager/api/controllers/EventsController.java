package edu.uniceub.tcc.calendareventmanager.api.controllers;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventCreateAllRequest;
import edu.uniceub.tcc.calendareventmanager.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/events")
public class EventsController {

    private final EventService eventService;

    public EventsController(final EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createEvents(@RequestBody final EventCreateAllRequest eventsRequest) {
        eventService.createEvents(eventsRequest.data(), eventsRequest.eventOwner());
    }
}
