package edu.uniceub.tcc.calendareventmanager.component.env;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractMongoContainer {
  static final MongoDBContainer MONGO_DB_CONTAINER;

  static {
    MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo").withTag("4.2.11"));
    MONGO_DB_CONTAINER.start();
  }

  @DynamicPropertySource
  static void dataSourceProperty(final DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }
}
