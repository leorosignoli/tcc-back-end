package edu.uniceub.tcc.calendareventmanager.repositories;

import edu.uniceub.tcc.calendareventmanager.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String>, EventCustomRepository {}
