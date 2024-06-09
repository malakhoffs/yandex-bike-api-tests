import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.*;


@RunWith(Parameterized.class)
public class CourierLoginFailTest {
    final private String login;
    final private String password;


    public CourierLoginFailTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Mike26", ""},
                {"", "12345"},
                {"", ""},
        };
    }

    @Test
    @DisplayName("Courier incorrect authorization")
    @Description("Checking that courier can not be authorized when mandatory fields are empty or incorrect data is entered")
    public void LogInAndCheckResponse() {
        RestAssured.baseURI = (Constants.HOST);
        CourierPOJO newCourier = new CourierPOJO(login, password);
        Response response = CourierSteps.courierLoginFailure(newCourier);
        response.then().statusCode(400)
                .and().body("message", is("Недостаточно данных для входа"));
    }
}
