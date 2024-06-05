import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    @Test
    public void requestOrderList(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get("/api/v1/orders");
        response.then().assertThat().statusCode(200)
        .and().body("orders.id", notNullValue()).body("orders.track", notNullValue());
        System.out.println(response.asString());
    }
}
