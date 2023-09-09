package edu.uniceub.tcc.calendareventmanager.helpers.mappers;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventCreationRequest;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toModel(EventCreationRequest eventCreationRequest);

    List<Event> toModelList(List<EventCreationRequest> eventCreationRequest);

}
