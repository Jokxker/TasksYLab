import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class TestRestApi {
    @Test
    public void testStartGameApiGet() {
        RestAssured.baseURI = "http://localhost:8080/gameplay";
        StringBuilder s = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/rating.txt")))) {
            String str;
            while ((str = reader.readLine()) != null) {
                s.append(str).append(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        when().request("GET").then().assertThat().statusCode(200).and()
                .body("data", is(s.toString()));
    }
}
