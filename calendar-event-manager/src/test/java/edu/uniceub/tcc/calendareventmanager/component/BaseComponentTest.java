package edu.uniceub.tcc.calendareventmanager.component;

import edu.uniceub.tcc.calendareventmanager.CalendarEventManagerApplication;
import edu.uniceub.tcc.calendareventmanager.component.env.AbstractMongoContainer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest(
    classes = {CalendarEventManagerApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class BaseComponentTest extends AbstractMongoContainer {
  private static final int WIRE_MOCK_PORT = 2345;
  @LocalServerPort protected long port;
  @Autowired protected MongoTemplate mongoTemplate;

  @BeforeAll
  public static void initClass() {}

  @BeforeEach
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.baseURI = String.format("http://localhost:%s", port);
  }

  @AfterAll
  public static void tearDown() {}
}
