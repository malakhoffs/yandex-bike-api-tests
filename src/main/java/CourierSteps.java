
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class CourierSteps {

    @Step ("Registering new courier data")
    public ValidatableResponse courierCreate(CourierPOJO courier) {
        return given()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step ("Log in using params of created courier")
    public ValidatableResponse courierLogin(CourierPOJO courier) {
        return given()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step ("Removing all dada of created courier")
    public ValidatableResponse courierDelete(CourierPOJO courier) {
        Integer id = courierLogin(courier)
                .extract().body().path("id");
        courier.setId(id);
        return given()
                .when()
                .delete("/api/v1/courier/"+courier.getId())
                .then();
    }
}
