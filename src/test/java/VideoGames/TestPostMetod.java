package VideoGames;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class TestPostMetod {

    public void testPostMetod() {
        String status = "Record Added Successfully";

        Games games = new Games(23, "GTA", "2006-04-23", 95, "Driving", "Mature");
        StatusPost statusPost;
        statusPost = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .body(games)
                .when()
                .contentType(ContentType.JSON)
                .post("app/videogames")
                .then().log().all()
                .extract().as(StatusPost.class);

        Assert.assertEquals(status, statusPost.getStatus());

    }

    @Test
    public void testDeleteMetod() {
        String status = "Record Deleted Successfully";

        StatusPost statusPost = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080")
                .when()
                .contentType(ContentType.JSON)
                .delete("/app/videogames/23")
                .then().log().all()
                .extract().as(StatusPost.class);

        Assert.assertEquals(status, statusPost.getStatus());

    }

    public void testGETResponce() {
        Games games = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .when()
                .contentType(ContentType.JSON)
                .get("app/videogames/10")
                .then().log().all()
                .extract().body().as(Games.class);

        Assert.assertTrue(games.getId() == 10);
        Assert.assertTrue(games.getName().equals("Grand Theft Auto III"));
        Assert.assertTrue(games.getReleaseDate().equals("2001-04-23"));
        Assert.assertTrue(games.getCategory().equals("Driving"));
        Assert.assertTrue(games.getReviewScore() == 100);
        Assert.assertTrue(games.getRating().equals("Mature"));


    }

    public void testPUTResonce() {
        Games games = new Games(10, "Grand Theft Auto III", "2006-04-23", 100, "Driving", "Mature");
        Games games1 = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .body(games)
                .when()
                .contentType(ContentType.JSON)
                .put("app/videogames/10")
                .then().log().body()
                .extract().as(Games.class);

        int i = 0;
    }
}
