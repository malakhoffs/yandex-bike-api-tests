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
                //Без имени курьер создается, даже есть полностью убрать ключ-значение из body, так что сорян :(
        };
    }

    @Test
    public void createNewCourierAndCheckResponseStatus(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        String json = "{\"login\": \"" + login + "\", \"password\": \"" + password + "\", \"firstName\": \"" + firstName +  "\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(400)
        .and().body("message", is("Недостаточно данных для создания учетной записи"));
        System.out.println(json);
        System.out.println(response.asString());
    }
}
