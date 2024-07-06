package files;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class DynamicJson {

    @Test(dataProvider = "bookdata")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response=given().log().all().header("Content-Type","application/json").body(Payload.Addbook(isbn, aisle)).
                when().post("Library/Addbook.php").
                then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(response);
        String id=js.getString("ID");
        System.out.println(id);
    }


    @DataProvider(name = "bookdata")
    public Object[][] main(){
        return new Object[][]{{"hello","123"},{"1234","4321"},{"jdnfjdf","ksdfk"}};
    }


}

