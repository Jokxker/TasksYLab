import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestRestApiPostBadRequest {
    @Test
    public void testStartGameApiPostBadRequest() { // Неправильный запрос
        RestAssured.baseURI = "http://localhost:8080/gameplay";
        given().
                param("name", "aleks").
                param("name0", "loki")
                .when().request("POST").then().body("status", is("BAD_REQUEST"));
    }
}
