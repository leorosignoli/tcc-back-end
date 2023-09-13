package edu.uniceub.tcc.calendareventmanager.helpers.mappers;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventRequest;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EventMapper {

  Event toModel(EventRequest eventCreateAllRequest);

  @Mapping(target = "owner", source = "eventOwner")
  default List<Event> toModelList(List<EventRequest> eventCreateAllRequest, String eventOwner) {

    return eventCreateAllRequest.stream()
        .map(this::toModel)
        .map(
            event -> {
              event.setOwner(eventOwner);
              return event;
            })
        .toList();
  }
}
