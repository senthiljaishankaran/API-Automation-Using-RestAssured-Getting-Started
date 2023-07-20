import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class xmlSchemaValidator {
    @Test
    void xmlSchemaValidator(){
        when()
                .get("http://127.0.0.1:5500/api.xml")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
    }
}
