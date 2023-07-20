import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class parsingXML {
    @Test
    void xmlParsingNormalValidation(){
        when()
                .get("http://127.0.0.1:5500/api.xml")
                .then()
                .statusCode(200)
                .body("TravelerinformationResponse.page",equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[1].name",equalTo("AS"));
    }
    @Test
    void xmParsingResponseValidation(){
        Response res=given()
                .when()
                .get("http://127.0.0.1:5500/api.xml");
        Assert.assertEquals(res.statusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml");
        String page=res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(page,"1");
        String travelerName=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[1].name").toString();
        Assert.assertEquals(travelerName,"AS");
    }
    @Test
    void xmlParsingLoopValidation(){
        Response res=given()
                .when()
                .get("http://127.0.0.1:5500/api.xml");
        XmlPath xmlPath=new XmlPath(res.asString());
        List<String> allTravellers=xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(allTravellers.size(),10);
        List<String> travelersName=xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        boolean status=false;
        for(String name:travelersName){
            if(name.equals("AS")){
                status=true;
                break;
            }
        }
        Assert.assertEquals(status,true);
    }
}
