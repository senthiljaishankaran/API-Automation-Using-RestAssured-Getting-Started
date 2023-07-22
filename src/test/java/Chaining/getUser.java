package Chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getUser {
    @Test
    void getUserTest(ITestContext context){
        int id=(Integer) context.getAttribute("user_id");
        String bearerToken="5f23e30aebc91c6166ba1d2c20fa149b02a467e912224f0e397d24c96f092779";

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParams("id",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
