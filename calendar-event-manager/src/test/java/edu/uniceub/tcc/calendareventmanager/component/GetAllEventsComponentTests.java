package edu.uniceub.tcc.calendareventmanager.component;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.uniceub.tcc.calendareventmanager.api.dtos.response.EventResponse;
import edu.uniceub.tcc.calendareventmanager.helpers.builders.EventBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class GetAllEventsComponentTests extends BaseComponentTest {

  private static final String GET_EVENTS_PATH = "/events";

  @Test
  void shouldReturnAllEvents() {
    var exampleEvent = EventBuilder.anEvent().withDefaultValues().build();
    mongoTemplate.dropCollection("events");

    mongoTemplate.insertAll(
        List.of(exampleEvent, EventBuilder.anEvent().withDefaultValues().build()));
    var responseList =
        given()
            .contentType(ContentType.JSON)
            .param("owner", "231231")
            .when()
            .get(GET_EVENTS_PATH)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body()
            .as(new TypeRef<List<EventResponse>>() {});

    responseList.forEach(
        event ->
            assertAll(
                () -> assertEquals(exampleEvent.getTitle(), event.title()),
                () -> assertEquals(exampleEvent.getStartDate(), event.startDate()),
                () -> assertEquals(exampleEvent.getEndDate(), event.endDate())));
  }
}
