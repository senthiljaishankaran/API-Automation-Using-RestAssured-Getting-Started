import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class fileUploadAndDownload {
    @Test(priority = 1)
    void singleFileUpload(){
        File file=new File("C:\\Users\\senth\\Downloads\\NewTextDocument.txt");
        given()
                .multiPart("file",file)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("fileName",equalTo("NewTextDocument.txt"));
    }
    //@Test
    void multipleFIleUploads(){
        File file1=new File("C:\\Users\\senth\\Downloads\\NewTextDocument.txt");
        File file2=new File("C:\\Users\\senth\\Downloads\\NewTextDocument2.txt");
        given()
                .multiPart("files",file1)
                .multiPart("files",file2)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("New Text Document.txt"))
                .body("[1].fileName",equalTo("New Text Document 2.txt"));
    }
    @Test(priority = 2)
    void fileDownload(){
        when()
                .get("http://localhost:8080/downloadFile/NewTextDocument.txt")
                .then()
                .statusCode(200);
    }
}
