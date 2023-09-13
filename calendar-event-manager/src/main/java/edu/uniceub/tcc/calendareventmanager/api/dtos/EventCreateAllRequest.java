package edu.uniceub.tcc.calendareventmanager.api.dtos;

import java.util.List;

public record EventCreateAllRequest(String eventOwner, List<EventRequest> data) {}
