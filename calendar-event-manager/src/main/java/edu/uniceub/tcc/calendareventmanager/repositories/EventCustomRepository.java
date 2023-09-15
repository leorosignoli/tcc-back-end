package edu.uniceub.tcc.calendareventmanager.repositories;

import edu.uniceub.tcc.calendareventmanager.models.Event;
import java.util.List;
import java.util.Optional;

public interface EventCustomRepository {

  Optional<Event> findByIntegrationId(String integrationId);

  List<Event> findAllByOwner(String owner);
}
