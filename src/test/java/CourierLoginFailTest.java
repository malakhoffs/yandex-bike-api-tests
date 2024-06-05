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
    public void courierLoginFailTest() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
            String json = "{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().statusCode(greaterThan(399));
        System.out.println(response.asString());
    }
}

