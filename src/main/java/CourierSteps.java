
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;



public class CourierSteps {


    public ValidatableResponse courierCreate(CourierPOJO courier) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    public ValidatableResponse courierLogin(CourierPOJO courier) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse courierDelete(CourierPOJO courier) {
        Integer id = courierLogin(courier)
                .extract().body().path("id");
        courier.setId(id);
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru/")
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/"+courier.getId())
                .then();
    }
}
