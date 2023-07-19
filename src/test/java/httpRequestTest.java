import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/*
given()
    will contain all pre-requisites for the API Automation testing
    like content type, set cookies,add auth,add param,set headers info etc..
When()
    will contain all the request methods
    like get(),post(),put(),patch(),delete()
Then()
    will contain all the Validation codes
    like status code extract response,extract headers, cookies and response body
 */
public class httpRequestTest {
    int id;
@Test (priority = 1)
    public void getUser(){
    when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .body("page",equalTo(2))
            .log().all();
}
@Test(priority = 2)
    public void postUser(){
    // Never use HashMap for data parsing in HashMap we can only give hard coded value
    // Find an alternate method
    HashMap<String,String> data=new HashMap<String,String>();
    data.put("name","senthil");
    data.put("job","QA");
    id=given()
            .contentType("application/json")
            .body(data)
            .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");
            // Here for getting the value of id from post method i am commenting the validation part of post method
//            .then()
//            .statusCode(201)
//            .log().all();

}
@Test (priority = 3,dependsOnMethods = {"postUser"})
    public void updateUser(){
    HashMap<String,String> putData=new HashMap<String,String>();
    putData.put("name","senthil");
    putData.put("job","SDET");
    given()
            .contentType("application/json")
            .body(putData)
            .when()
            .put("https://reqres.in/api/users/"+id)
            .then()
            .statusCode(200)
            .log().all();
}
@Test(priority = 4,dependsOnMethods = {"postUser"})
    public void deleteUser(){
    given()
            .when()
            .delete("https://reqres.in/api/users/"+id)
            .then()
            .statusCode(204)
            .log().all();
}
}
