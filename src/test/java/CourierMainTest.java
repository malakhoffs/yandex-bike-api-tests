import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierMainTest extends MainTestsSetUp {
    CourierPOJO courier;
    private final CourierSteps creationSteps = new CourierSteps();


    @Before
    public void setUp() {
    courier = new CourierPOJO();
    courier.setLogin(RandomStringUtils.randomAlphabetic(8));
    courier.setPassword(RandomStringUtils.randomAlphabetic(8));

    }

    @Test
    @DisplayName("New courier correct creation")
    @Description("Checking that new courier may be created when fields are filled correctly")
    public void courierCreationTest () {
        creationSteps.courierCreate(courier)
                .assertThat().statusCode(201).and().body("ok", is(true));
    }

    @Test
    @DisplayName("Courier duplicate creation permission")
    @Description("Checking that courier can not be created when using data of already created courier")
    public void courierDuplicationCreationTest () {
        creationSteps.courierCreate(courier);
        creationSteps.courierCreate(courier)
                .assertThat().statusCode(409).and().body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Courier correct authorization")
    @Description("Checking that courier may authorized when fields are filled correctly")
    public void courierLoginTest () {
        creationSteps.courierCreate(courier);
        creationSteps.courierLogin(courier)
                .assertThat().statusCode(200).and().body("id", notNullValue());
    }

    @After
    public void courierDelete () {
        creationSteps.courierDelete(courier)
                .assertThat().statusCode(200).and().body("ok", is(true));
    }
}
