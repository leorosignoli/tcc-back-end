package edu.uniceub.tcc.calendareventmanager.services;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventCreationRequest;
import edu.uniceub.tcc.calendareventmanager.helpers.mappers.EventMapper;
import edu.uniceub.tcc.calendareventmanager.helpers.monad.Monad;
import edu.uniceub.tcc.calendareventmanager.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(final EventRepository eventRepository, final EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }
    public void createEvents(final List<EventCreationRequest> eventCreationRequests) {
        Monad.init(eventCreationRequests)
                .applyFunction(eventMapper::toModelList)
                .applyConsumer(eventRepository::saveAll);
    }
}
