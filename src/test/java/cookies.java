import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
public class cookies {
    @Test
    void cookiesTest(){
        Response res=given()
        .when()
                .get("https://stackoverflow.com/");
                //getting cookie info
        String cookiesInfo=res.getCookie("prov");
        System.out.println("The Value of prov cookie is: "+cookiesInfo);

        // Getting all cookies info
        Map<String,String> cookie_values=res.getCookies();
        for(String key_value:cookie_values.keySet()){
            String cookie_value=res.cookie(key_value);
            System.out.println(key_value+"  "+cookie_value);
        }
    }
}
