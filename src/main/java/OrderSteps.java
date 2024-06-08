import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Creating new order")
    public static Response createOrder(OrderPOJO order) {
        return given()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post(Constants.ORDERS);
    }

    @Step("Showing all created orders")
    public static Response showAllOrders () {
        return given()
                    .header("Content-type", "application/json")
                    .when()
                    .get(Constants.ORDERS);
    }
}
