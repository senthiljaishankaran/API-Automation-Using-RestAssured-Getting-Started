import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Authorization {
    @Test
    void basicAuth(){
        given()
                .auth().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true));
    }
    @Test
    void digestAuth(){
        given()
                .auth().digest("postman","password")
                .when()
                .get("https://postman-echo.com/digest-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true));
    }
    @Test
    void preemptiveAuth(){
        // preemptive is a basic auth type that gives the username and password before the 401 response from the server
        // stating No authorisation
        given()
                .auth().preemptive().basic("postman","password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true));
    }
    @Test
    void bearerToken(){
        /*
        Bearer Token - User will provide authentication ,and they will request for access token
        System will provide user with an access token and  refresh token
        re-fresh token is used to generate access token once again if the access token is expired
        access token life span only 15 min
        re-fresh token min life span is 7 days
         */
        String bearerTokenValue="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";
        given()
                .headers("Authorization","Bearer "+bearerTokenValue)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200);
    }
    // Method 1 for API key
    @Test
    void apiKey1(){
        given()
                .queryParam("appid","c1ba3f78929988d5bb8d57a758e1b954")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99")
                .then()
                .statusCode(200);
    }
    // Method 2 for API Key
    @Test
    void apikey2(){
        given()
                .queryParam("appid","c1ba3f78929988d5bb8d57a758e1b954")
                .pathParams("path","data/2.5/weather")
                .queryParam("lat","44.34")
                .queryParam("lon","10.99")
                .when()
                .get("https://api.openweathermap.org/{path}")
                .then()
                .statusCode(200);
    }
}
