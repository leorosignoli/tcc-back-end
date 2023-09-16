package edu.uniceub.tcc.calendareventmanager.repositories.impl;

import static org.slf4j.LoggerFactory.getLogger;

import edu.uniceub.tcc.calendareventmanager.helpers.monad.Monad;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import edu.uniceub.tcc.calendareventmanager.repositories.EventCustomRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventCustomRepositoryImpl implements EventCustomRepository {

  private final MongoTemplate mongoTemplate;

  private static final Logger LOGGER = getLogger(EventCustomRepositoryImpl.class);

  public EventCustomRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Optional<Event> findByIntegrationId(String integrationId) {

    final var query = new Query(Criteria.where("integrationId").is(integrationId));
    return Optional.ofNullable(mongoTemplate.findOne(query, Event.class, "event"));
  }

  @Override
  public List<Event> findAllByOwnerAndDate(String owner, LocalDateTime startDate) {

    return Monad.init(
            new Query(
                Criteria.where("owner")
                    .is(owner)
                    .and("isDeleted")
                    .is(false)
                    .and("startDate")
                    .regex("^" + startDate.toLocalDate().toString(), "i")))
        .applyLogger(query -> LOGGER.info("Searching with query {}", query))
        .applyFunction(query -> mongoTemplate.find(query, Event.class, "event"))
        .getValue();
  }
}
