import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@RunWith(Parameterized.class)
public class OrderCreationTest {
    final private String blackColor;
    final private String greyColor;


    public OrderCreationTest(String login, String password) {
        this.blackColor = login;
        this.greyColor = password;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"BLACK", ""},
                {"GREY", ""},
                {"BLACK", "GREY"},
                {"",""}
        };
    }


    @Test
    public void createNewOrderAndCheckResponseStatus() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        String [] color = {blackColor, greyColor};
        OrderPOJO newOrder = new OrderPOJO("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", "5", "2020-06-06", "Saske, come back to Konoha", color);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(newOrder)
                        .when()
                        .post("/api/v1/orders");
        response.then().statusCode(is(201)).and().body("track",notNullValue());
        System.out.println(newOrder);
        System.out.println(response.asString());
    }
}
