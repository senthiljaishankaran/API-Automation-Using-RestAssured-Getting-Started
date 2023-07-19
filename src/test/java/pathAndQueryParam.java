import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class pathAndQueryParam {
@Test
 void pathAndQuery(){
   // https://reqres.in/api/users?page=2&id=5
    given()
            .pathParams("myPath","users")
            .queryParam("page","2")
            .queryParam("id","5")
            .when()
            .get("https://reqres.in/api/{myPath}")
            .then()
            .statusCode(200)
            .log().all();
}
}
