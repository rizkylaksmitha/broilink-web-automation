package stepDef;

import pages.LandingPage;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions; // 🔥 SEKARANG PAKAI JUNIT 5 JUPITER
import org.openqa.selenium.WebDriver;

public class LandingPageStepDef {
    WebDriver driver = DriverManager.getDriver();
    LandingPage landingPage = new LandingPage(driver);

    @Given("Browser dalam kondisi aktif")
    public void browser_dalam_kondisi_aktif() {
        // JUnit 5
        Assertions.assertNotNull(driver);
    }

    @When("User membuka URL BroiLink {string}")
    public void user_membuka_url_broi_link(String url) {
        driver.get(url);
    }

    @Then("Landing Page berhasil ditampilkan")
    public void landing_page_berhasil_ditampilkan() {
        // JUnit 5
        Assertions.assertTrue(landingPage.isHeroSectionDisplayed());
    }

    @Given("Landing Page terbuka")
    public void landing_page_terbuka() {
        landingPage.bukaHalamanUtama();
    }

    @When("User mengklik tombol Gabung")
    public void user_mengklik_tombol_gabung() {
        landingPage.klikTombolGabung();
    }

    @Then("Sistem mengarahkan ke halaman Login")
    public void sistem_megarahkan_ke_halaman_login() {
        // JUnit 5
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }
}