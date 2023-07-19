import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class headersAndLogs {
    @Test
    void headersTest(){
        Response res=when()
                .get("https://reqres.in/api/users?page=2");
        String headerServer=res.getHeader("server");
        System.out.println("Header value for server is: "+ headerServer);

        Headers allHeaders=res.getHeaders();
        for(Header headers:allHeaders){
            System.out.println(headers.getValue()+"  "+headers.getName());
        }
    }
    @Test
    void logging(){
        when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().headers()
                .log().cookies()
                .log().body();
    }
}
