package edu.uniceub.tcc.calendareventmanager.services;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventRequest;
import edu.uniceub.tcc.calendareventmanager.helpers.mappers.EventMapper;
import edu.uniceub.tcc.calendareventmanager.helpers.monad.Monad;
import edu.uniceub.tcc.calendareventmanager.repositories.EventRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  public EventService(final EventRepository eventRepository, final EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  public void createEvents(
      final List<EventRequest> eventCreateAllRequests, final String eventOwner) {
    Monad.init(eventCreateAllRequests)
        .applyFunction(event -> eventMapper.toModelList(eventCreateAllRequests, eventOwner))
        .applyConsumer(eventRepository::saveAll);
  }
}
