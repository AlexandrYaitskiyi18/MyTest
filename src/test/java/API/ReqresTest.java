package API;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ReqresTest {

    public static final String URL = "https://reqres.in/";

    @Test
    public void checkGetTest() {

        List<UsersDate> users = given()
               // .baseUri("https://reqres.in/")
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UsersDate.class);


    }
}
