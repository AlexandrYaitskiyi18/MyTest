package videoGameDB;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestGetGames {
    @Test
    public void testGetGames(){

        ResponseBody responseBody =  given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)

                .when()
                .get("/app/videogames");


        System.out.println(responseBody.print());
        //.then().statusCode(200);
    }
@Test
    public void testGetGame(){

        Response response = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080")

                .when()
                .get("/app/videogames/1");

        GameModel gameModel = response.as(GameModel.class);
        Assert.assertTrue(gameModel.id == 1);
        Assert.assertTrue(gameModel.name.equals("Resident Evil 4"));
        Assert.assertTrue(gameModel.releaseDate.equals("2005-10-01"));
        Assert.assertTrue(gameModel.reviewScore == 85);
        Assert.assertTrue(gameModel.category.equals("Shooter"));
        Assert.assertTrue(gameModel.rating.equals("Universal"));
int i=1;
    }

}
