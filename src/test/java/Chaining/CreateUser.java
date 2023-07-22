package Chaining;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {
    @Test
    void createUser(ITestContext context){
        Faker faker=new Faker();
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("Gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("Status","inactive");

        System.out.println(data.get("name"));

        String bearerToken="5f23e30aebc91c6166ba1d2c20fa149b02a467e912224f0e397d24c96f092779";

        int id=given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
        System.out.println("Generated id is: " + id);
        context.setAttribute("user_id",id);
    }
}
