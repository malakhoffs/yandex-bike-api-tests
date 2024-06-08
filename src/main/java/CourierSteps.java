
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class CourierSteps {


    @Step ("Registering new courier data")
    public ValidatableResponse courierCreate(CourierPOJO courier) {
        return given()
                .body(courier)
                .when()
                .post(Constants.COURIER)
                .then();
    }

    @Step ("Log in using params of created courier")
    public ValidatableResponse courierLogin(CourierPOJO courier) {
        return given()
                .body(courier)
                .when()
                .post(Constants.LOGIN)
                .then();
    }

    @Step ("Removing all dada of created courier")
    public void courierDelete (CourierPOJO courier) {
        Integer id = courierLogin(courier)
                .extract().body().path("id");
        courier.setId(id);
        given()
                .when()
                .delete("/api/v1/courier/" + courier.getId())
                .then();
    }

    @Step ("Registering new courier with illegal data")
    public static Response courierFailure (CourierPOJO courier) {
        RestAssured.baseURI = (Constants.HOST);
                return given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(Constants.COURIER);
   }
    @Step ("Logging in with illegal data")
    public static Response courierLoginFailure (CourierPOJO courier) {
        RestAssured.baseURI = (Constants.HOST);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(Constants.LOGIN);
    }
}
