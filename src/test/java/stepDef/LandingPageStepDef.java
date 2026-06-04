package stepDef;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.LandingPage;
import utils.DriverManager;

public class LandingPageStepDef {

    LandingPage landingPage;

    @Given("user membuka landing page")
    public void user_membuka_landing_page() {

        DriverManager.getDriver()
                .get("http://localhost:5173");

        landingPage =
                new LandingPage(DriverManager.getDriver());
    }

    @Then("landing page berhasil tampil")
    public void landing_page_berhasil_tampil() {

        Assert.assertTrue(
                landingPage.isLogoDisplayed()
        );
    }

    @Then("navbar tampil")
    public void navbar_tampil() {

        Assert.assertTrue(
                landingPage.isNavbarDisplayed()
        );
    }

    @When("user klik tombol gabung")
    public void user_klik_tombol_gabung() {

        landingPage.clickGabungButton();
    }

    @Then("berpindah ke halaman login")
    public void berpindah_ke_halaman_login() {

        String currentUrl =
                DriverManager.getDriver().getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("login")
        );
    }
}