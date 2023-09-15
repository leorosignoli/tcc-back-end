package edu.uniceub.tcc.calendareventmanager.repositories.impl;

import edu.uniceub.tcc.calendareventmanager.models.Event;
import edu.uniceub.tcc.calendareventmanager.repositories.EventCustomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

  private final MongoTemplate mongoTemplate;

  public EventCustomRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Optional<Event> findByIntegrationId(String integrationId) {

    final var query = new Query(Criteria.where("integrationId").is(integrationId));
    return Optional.ofNullable(mongoTemplate.findOne(query, Event.class, "event"));
  }

  @Override
  public List<Event> findAllByOwner(String owner) {
    final var query = new Query(Criteria.where("owner").is(owner));

    return mongoTemplate.find(query, Event.class, "event");
  }
}
