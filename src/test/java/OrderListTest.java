import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    @Test
    @DisplayName("Getting order list")
    @Description("Checking that order list is not empty when getting")
    public void requestOrderList(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .when()
                        .get("/api/v1/orders");
        response.then().assertThat().statusCode(200)
        .and().body("orders.id", notNullValue()).body("orders.track", notNullValue());
    }
}
