package edu.uniceub.tcc.calendareventmanager.helpers.mappers;

import edu.uniceub.tcc.calendareventmanager.api.dtos.EventCreateAllRequest;
import edu.uniceub.tcc.calendareventmanager.api.dtos.EventRequest;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toModel(EventCreateAllRequest eventCreateAllRequest);

    @Mapping(target = "owner", source = "eventOwner")
    List<Event> toModelList(List<EventRequest> eventCreateAllRequest, String eventOwner);

}
