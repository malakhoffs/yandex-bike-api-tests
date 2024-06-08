import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    @Test
    @DisplayName("Getting order list")
    @Description("Checking that order list is not empty when getting")
    public void requestOrderListAndCheckResponse(){
        RestAssured.baseURI = (Constants.HOST);
        OrderSteps.showAllOrders()
        .then().assertThat().statusCode(200)
        .and().body("orders.id", notNullValue()).body("orders.track", notNullValue());
    }
}
