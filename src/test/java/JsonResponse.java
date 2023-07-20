import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class JsonResponse {
@Test
    public void getJsonResponse(){
    Response res=given()
            .contentType(ContentType.JSON)
            .when()
            .get("https://reqres.in/api/users?page=2");
    Assert.assertEquals(res.getStatusCode(),200);
    Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
    // Using Json path to Validate the response
    String first_name=res.jsonPath().get("data[0].first_name").toString();
    Assert.assertEquals(first_name,"Michael");

    // Passing response to Json Object as String
    JSONObject jsonObject=new JSONObject(res.asString());
    for(int i=0;i<jsonObject.getJSONArray("data").length();i++){
        String name=jsonObject.getJSONArray("data").getJSONObject(i).get("first_name").toString();
        System.out.println(name);
    }
}
}
