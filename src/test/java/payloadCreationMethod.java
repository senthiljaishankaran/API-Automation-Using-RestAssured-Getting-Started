import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class payloadCreationMethod {
    // Creating Payload using HashMap
   // @Test
    void postTestUsingHashMap(){
        HashMap data =new HashMap();
        data.put("name","senthil");
        data.put("role","SDET");
        String lang[]={"java","javascript"};
        data.put("languages",lang);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("senthil"))
                .body("role",equalTo("SDET"))
                .body("languages[0]",equalTo("java"))
                .log().all();
    }
    // Creating Payload using JSON.org
    //@Test
    void postTestUsingJson(){
        JSONObject jsonData=new JSONObject();
        jsonData.put("name","senthil");
        jsonData.put("role","SDET");
        String lang[]={"java","javascript"};
        jsonData.put("languages",lang);

        given()
                .contentType("application/json")
                .body(jsonData.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("senthil"))
                .body("role",equalTo("SDET"))
                .body("languages[0]",equalTo("java"))
                .log().all();
    }
    // Creating Payload Using POJO Class
   // @Test
    void postTestUsingPojoClass(){
        pojoData dataPojo=new pojoData();
        dataPojo.setName("senthil");
        dataPojo.setRole("SDET");
        String[] lang={"java","javascript"};
        dataPojo.setLang(lang);

        given()
                .contentType("application/json")
                .body(dataPojo)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("senthil"))
                .body("role",equalTo("SDET"))
                .body("lang[0]",equalTo("java"))
                .log().all();
    }
    // Creating Payload from external Json file
    @Test
    void postTestUsingExternalJsonFile() throws FileNotFoundException {
        File file=new File("C:\\Users\\senth\\IdeaProjects\\RestAssuredGettingStarted\\src\\main\\resources\\body.json");
        FileReader fileReader=new FileReader(file);
        JSONTokener jsonTokener=new JSONTokener(fileReader);
        JSONObject jsonObject=new JSONObject(jsonTokener);
        given()
                .contentType("application/json")
                .body(jsonObject.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("senthil"))
                .body("role",equalTo("SDET"))
                .body("lang[0]",equalTo("java"))
                .log().all();
    }
}
