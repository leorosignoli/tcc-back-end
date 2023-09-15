package edu.uniceub.tcc.calendareventmanager.api.dtos.request;

import java.util.List;

public record EventCreateAllRequest(String eventOwner, List<EventRequest> data) {}
