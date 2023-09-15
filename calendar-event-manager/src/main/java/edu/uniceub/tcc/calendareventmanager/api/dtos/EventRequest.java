package edu.uniceub.tcc.calendareventmanager.api.dtos;

public record EventRequest(String title, String startDate, String endDate, String integrationId) {}
