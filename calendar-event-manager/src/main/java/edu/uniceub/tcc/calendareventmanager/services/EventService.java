package edu.uniceub.tcc.calendareventmanager.services;

import static org.slf4j.LoggerFactory.getLogger;

import edu.uniceub.tcc.calendareventmanager.api.dtos.request.EventRequest;
import edu.uniceub.tcc.calendareventmanager.api.dtos.response.EventResponse;
import edu.uniceub.tcc.calendareventmanager.helpers.mappers.EventMapper;
import edu.uniceub.tcc.calendareventmanager.helpers.monad.Monad;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import edu.uniceub.tcc.calendareventmanager.repositories.EventRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.UnaryOperator;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private static final Logger LOGGER = getLogger(EventService.class);

  private EventRepository eventRepository;
  private final EventMapper eventMapper;

  public EventService(final EventRepository eventRepository, final EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  public void createEvent(EventRequest eventRequest, String owner) {
    Monad.init(eventRequest)
        .applyFunction(request -> eventMapper.toModel(request, owner))
        .applyConsumer(eventRepository::save)
        .applyLogger(event -> LOGGER.info("Event created: {}", event));
  }

  public List<String> createEvents(
      final List<EventRequest> eventCreateAllRequests, final String eventOwner) {
    return Monad.init(eventCreateAllRequests)
        .applyFunction(event -> eventMapper.toModelList(eventCreateAllRequests, eventOwner))
        .applyFunction(removeAllExistingEventsFromList)
        .applyLogger(events -> LOGGER.info("Events remaining after filtering: {}", events))
        .applyConsumer(eventRepository::saveAll)
        .getValue()
        .stream()
        .map(Event::getTitle)
        .toList();
  }

  public List<EventResponse> getEventsForOwner(String eventOwner, String startDate) {

    return Monad.init(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .applyFunction(formatter -> LocalDate.parse(startDate, formatter))
        .applyFunction(date -> eventRepository.findAllByOwnerAndDate(eventOwner, date))
        .applyLogger(events -> LOGGER.debug("Events found for owner {}: {}", eventOwner, events))
        .applyFunction(eventMapper::toResponseList)
        .getValue();
  }

  private final UnaryOperator<List<Event>> removeAllExistingEventsFromList =
      events ->
          events.stream()
              .filter(
                  event -> eventRepository.findByIntegrationId(event.getIntegrationId()).isEmpty())
              .toList();
}
