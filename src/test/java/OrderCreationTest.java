import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
    @DisplayName("Order correct creation")
    @Description("Checking that order may be created with various filling of COLOUR field and all other fields are filed correctly")
    public void createNewOrderAndCheckResponse() {
        RestAssured.baseURI = (Constants.HOST);
        String [] color = {blackColor, greyColor};
        OrderPOJO newOrder = new OrderPOJO("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", "5", "2020-06-06", "Saske, come back to Konoha", color);
        Response response = OrderSteps.createOrder(newOrder);
        response.then().statusCode(is(201)).and().body("track",notNullValue());
    }
}
