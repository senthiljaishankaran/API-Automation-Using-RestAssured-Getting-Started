package Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class updateUser {
@Test
    void updateUserTest(ITestContext context){
    Faker faker=new Faker();
    JSONObject data=new JSONObject();
    data.put("name",faker.name().fullName());
    data.put("Gender","Male");
    data.put("email",faker.internet().emailAddress());
    data.put("Status","active");

    System.out.println(data.get("name"));

    String bearerToken="5f23e30aebc91c6166ba1d2c20fa149b02a467e912224f0e397d24c96f092779";

    int id=(Integer) context.getAttribute("user_id");
    given()
            .headers("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(data.toString())
            .when()
            .put("https://gorest.co.in/public/v2/users{id}")
            .then()
            .statusCode(200)
            .log().all();
}
}
