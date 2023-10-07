package edu.uniceub.tcc.calendareventmanager.component;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import edu.uniceub.tcc.calendareventmanager.helpers.builders.EventRequestBuilder;
import edu.uniceub.tcc.calendareventmanager.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;

class SaveEventComponentTest extends BaseComponentTest {

  private static final String POST_EVENT_PATH = "/events/simpleEvent";

  @Test
  void shouldSaveToDatabaseWhenRequestIsValid() {
    final var eventRequest = EventRequestBuilder.withDefaultValues();

    given()
        .contentType(io.restassured.http.ContentType.JSON)
        .body(eventRequest)
        .header("owner", "owner")
        .when()
        .post(POST_EVENT_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value());
    final var findByOwnerQuery = new Query(Criteria.where("eventOwner").is("owner"));

    mongoTemplate
        .find(findByOwnerQuery, Event.class)
        .forEach(
            event -> {
              assertAll(
                  () -> assertEquals("owner", event.getOwner()),
                  () -> assertEquals("title", event.getTitle()),
                  () -> assertEquals("2021-01-01T00:00:00", event.getStartDate()),
                  () -> assertEquals("2021-01-02T00:00:00", event.getEndDate()),
                  () -> assertTrue(event.getDeleted()));
            });
    mongoTemplate.dropCollection("events");
  }
}
