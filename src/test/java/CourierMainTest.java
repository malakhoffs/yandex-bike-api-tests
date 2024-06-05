import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierMainTest {
    CourierPOJO courier;
    private final CourierSteps creationSteps = new CourierSteps();


    @Before
    public void setUp() {
    courier = new CourierPOJO();
    courier.setLogin(RandomStringUtils.randomAlphabetic(8));
    courier.setPassword(RandomStringUtils.randomAlphabetic(8));

    }

    @Test
    public void courierCreationTest () {
        creationSteps.courierCreate(courier)
                .assertThat().statusCode(201).and().body("ok", is(true));
        System.out.println(courier);
    }

    @Test
    public void courierDuplicationCreationTest () {
        creationSteps.courierCreate(courier);
        creationSteps.courierCreate(courier)
                .assertThat().statusCode(409).and().body("message", is("Этот логин уже используется. Попробуйте другой."));
        System.out.println(courier);
    }

    @Test
    public void courierLoginTest () {
        creationSteps.courierCreate(courier);
        creationSteps.courierLogin(courier)
                .assertThat().statusCode(200).and().body("id", notNullValue());
        System.out.println(courier);
    }

    @After
    public void courierDelete () {
        creationSteps.courierDelete(courier)
                .assertThat().statusCode(200).and().body("ok", is(true));
    }
}
