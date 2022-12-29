package APITestVideoGamesDB;
import VideoGames.StatusPost;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;


public class TestVideoGamesDB {


    // перевірка GET - запиту
    @Test
    public void testGetRequest() {
        List<GamesModelPojo> gamesModelPojo = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .when()
                .contentType(ContentType.JSON)
                .get("app/videogames")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GamesModelPojo.class);
    }

    // перевірка POST - запиту
    @Test
    public void testPostRequest() {
        String statusPost = "Record Added Successfully";
        GamesModelPojo gamesModelPojo = new GamesModelPojo(200, "Dragon Age", "2016-12-24", 90, "RPG", "Super");
        StatusModelPojo statusModelPojo = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .baseUri("http://localhost:8080/")
                .body(gamesModelPojo)
                .when()
                .contentType(ContentType.JSON)
                .post("app/videogames")
                .then().log().all()
                .extract().as(StatusModelPojo.class);

        Assert.assertEquals(statusModelPojo.getStatus(), statusPost);

    }

    // перевірка GET(однієї гри) - запиту
    @Test
    public void testGetRequestOneElement() {
        GamesModelPojo gamesModelPojo = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .when()
                .contentType(ContentType.JSON)
                .get("app/videogames/1")
                .then().log().all()
                .extract().body().as(GamesModelPojo.class);

        Assert.assertEquals((int) gamesModelPojo.getId(), 1);
        Assert.assertEquals(gamesModelPojo.getName(), "Resident Evil 4");
        Assert.assertEquals(gamesModelPojo.getReleaseDate(), "2005-10-01");
        Assert.assertEquals((int) gamesModelPojo.getReviewScore(), 85);
        Assert.assertEquals(gamesModelPojo.getCategory(), "Shooter");
        Assert.assertEquals(gamesModelPojo.getRating(), "Universal");


    }

    // перевірка PUT - запиту
    @Test
    public void testPutRequest() {
        GamesModelPojo gamesModelPojo = new GamesModelPojo(6, "Albion ", "2022-12-24", 75, "RPG online", "Not bed");
        given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .body(gamesModelPojo)
                .when()
                .contentType(ContentType.JSON)
                .put("app/videogames/6")
                .then().log().all();


    }

    // перевірка DELETE - запиту
    @Test
    public void testDeleteRequest() {
        String statusDelete = "Record Deleted Successfully";
        StatusPost statusPost = given()
                .header("Accept", "application/json")
                .baseUri("http://localhost:8080/")
                .when()
                .contentType(ContentType.JSON)
                .delete("app/videogames/10")
                .then().log().all()
                .extract().body().as(StatusPost.class);

        Assert.assertEquals(statusDelete, statusPost.getStatus());


    }

    private static final String URL = "http://localhost:8080/";
    private static final String HEADER = "Accept";
    private static final String BODYHEADER = "application/json";
    private static final String URLPart2 = "app/videogames/";
    private static final int POSITIVESTATUSCODE = 200;

    // перевірка GET - запиту
    @Test
    public void testGetRequestModern() {
        Response response = given()
                .header(HEADER, BODYHEADER)
                .baseUri(URL)
                .when()
                .contentType(ContentType.JSON)
                .get(URLPart2)
                .then().log().all()
                .extract().response();
        List<GamesModelPojo> gamesModelPojoList = response.jsonPath().getList(".", GamesModelPojo.class);

        Assert.assertEquals(response.statusCode(), POSITIVESTATUSCODE);

    }

    // перевірка POST - запиту
    @Test
    public void testPostRequestModern() {
        String statusModel = "Record Added Successfully";
        GamesModelPojo gamesModelPojo = new GamesModelPojo(201, "Avatar", "2022-12-24", 100, "RPG online", "Very good");
        Response response = given()
                .header(HEADER, BODYHEADER)
                .baseUri(URL)
                .body(gamesModelPojo)
                .when()
                .contentType(ContentType.JSON)
                .post(URLPart2)
                .then().log().all()
                .extract().response();
        StatusModelPojo statusModelPojo = response.as(StatusModelPojo.class);

        Assert.assertEquals(response.statusCode(), POSITIVESTATUSCODE);
        Assert.assertEquals(statusModelPojo.getStatus(), statusModel);
    }

    // перевірка PUT - запиту
    @Test
    public void testPutRequestModern() {
        GamesModelPojo gamesModelPojo = new GamesModelPojo(5, "Avatar", "2022-12-24", 100, "RPG online", "Very good");
        Response response = given()
                .header(HEADER, BODYHEADER)
                .baseUri(URL)
                .body(gamesModelPojo)
                .when()
                .contentType(ContentType.JSON)
                .put(URLPart2 + "5")
                .then().log().all()
                .extract().response();

        GamesModelPojo gamesModelPojo1 = response.as(GamesModelPojo.class);

        Assert.assertEquals(response.statusCode(), POSITIVESTATUSCODE);

        Assert.assertEquals((int) gamesModelPojo1.getId(), 5);
        Assert.assertEquals(gamesModelPojo1.getName(), "Avatar");
        Assert.assertEquals(gamesModelPojo1.getReleaseDate(), "2022-12-24");
        Assert.assertEquals((int) gamesModelPojo1.getReviewScore(), 100);
        Assert.assertEquals(gamesModelPojo1.getCategory(), "RPG online");
        Assert.assertEquals(gamesModelPojo1.getRating(), "Very good");

    }

    // перевірка GET - запиту
    @Test
    public void testGetRequestModern1() {
        Response response = given()
                .header(HEADER, BODYHEADER)
                .baseUri(URL)
                .when()
                .contentType(ContentType.JSON)
                .get(URLPart2 + "2")
                .then().log().all()
                .extract().response();

        GamesModelPojo gamesModelPojo = response.as(GamesModelPojo.class);

        Assert.assertEquals(response.statusCode(), POSITIVESTATUSCODE);

        Assert.assertEquals((int) gamesModelPojo.getId(), 2);
        Assert.assertEquals(gamesModelPojo.getName(), "Gran Turismo 3");
        Assert.assertEquals(gamesModelPojo.getReleaseDate(), "2001-03-10");
        Assert.assertEquals((int) gamesModelPojo.getReviewScore(), 91);
        Assert.assertEquals(gamesModelPojo.getCategory(), "Driving");
        Assert.assertEquals(gamesModelPojo.getRating(), "Universal");

    }
    // перевірка Delete - запиту
    @Test
    public void testDeleteResponseModern() {
        String statusDelete = "Record Deleted Successfully";
        Response response = given()
                .header(HEADER, BODYHEADER)
                .baseUri(URL)
                .when()
                .contentType(ContentType.JSON)
                .delete(URLPart2 + "9")
                .then().log().all()
                .extract().response();
        StatusModelPojo statusModelPojo = response.as(StatusModelPojo.class);

        Assert.assertEquals(response.statusCode(), POSITIVESTATUSCODE);
        Assert.assertEquals(statusModelPojo.getStatus(), statusDelete);

    }

}
