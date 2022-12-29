package VideoGames;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestGame {
@Test
    public void testGameList(){
            List<Games> games = given()
                    .header("Accept", "application/json")
                    .baseUri("http://localhost:8080")
                    .contentType(ContentType.JSON)
                    .when()
                    .get("app/videogames")
                    .then().log().all()
                    .extract().body().jsonPath().getList(".", Games.class);






    }
}
