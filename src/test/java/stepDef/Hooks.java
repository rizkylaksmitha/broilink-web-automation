package stepDef;

import utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver(); // pastikan driver sudah siap
    }

    @After
    public void tearDown() {

    }
}