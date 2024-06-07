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
public class CourierCreationFailTest {

    final private String login;
    final private String password;
    final private String firstName;

    public CourierCreationFailTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"", "12345","Mike"},
                {"Mike26", "", "Mike"}
        };
    }

    @Test
    @DisplayName("Courier creation empty fields")
    @Description("Checking that courier can not be created without login or password")
    public void createNewCourierAndCheckResponseStatus(){
        RestAssured.baseURI = (Constants.HOST);
        CourierPOJO newRestrictedCourierCreation = new CourierPOJO (login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(newRestrictedCourierCreation)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(400)
        .and().body("message", is("Недостаточно данных для создания учетной записи"));
    }
}
