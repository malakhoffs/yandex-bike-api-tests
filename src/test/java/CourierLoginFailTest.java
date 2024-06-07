import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
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
                {"&%b7B8n(&N","6gIN78n8gT^&*n"}
        };
    }

    @Test
    @DisplayName("Courier incorrect authorization")
    @Description("Checking that courier can not be authorized when mandatory fields are empty or incorrect data is entered")
    public void courierLoginFailTest() {
        RestAssured.baseURI = (Constants.HOST);
        CourierPOJO newRestrictedLogIn = new CourierPOJO (login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(newRestrictedLogIn)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(greaterThan(399));
    }
}

