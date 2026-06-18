package stepDef;

import pages.LoginPage;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginStepDef {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("Halaman login terbuka")
    public void halaman_login_terbuka() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(this.driver);

        driver.get("http://localhost:5173/login");

        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @When("User memasukkan username {string} dan password {string}")
    public void user_memasukkan_username_dan_password(String username, String password) {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
    }

    @When("User mengklik tombol Masuk")
    public void user_mengklik_tombol_masuk() {
        loginPage.klikMasuk();
    }

    @Then("Berhasil login dan masuk ke Dashboard Admin")
    public void berhasil_login_dan_masuk_ke_dashboard_admin() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Then("Sistem menampilkan pesan error login")
    public void sistem_menampilkan_pesan_error_login() {
        try { Thread.sleep(1000); } catch (Exception e) {}
        boolean isErrorVisible = driver.getPageSource().contains("salah") ||
                driver.getPageSource().contains("invalid") ||
                !loginPage.getErrorMessage().isEmpty();

        Assertions.assertTrue(isErrorVisible, "Sistem tidak menampilkan pesan error login yang diharapkan!");
    }

    @Then("Sistem menolak login atau menampilkan pesan validasi")
    public void sistem_menolak_login_atau_menampilkan_pesan_validasi() {
        try { Thread.sleep(1000); } catch (Exception e) {}
        boolean isValidationTriggered = driver.getPageSource().contains("required") ||
                driver.getPageSource().contains("kosong") ||
                driver.getCurrentUrl().contains("login");

        Assertions.assertTrue(isValidationTriggered, "Sistem tidak menolak login pada partition kosong!");
    }

    @Then("Sistem menerima input password")
    public void sistem_menerima_input_password() {
        try { Thread.sleep(1500); } catch (Exception e) {}
        boolean loginBerhasilAtauInputValid = driver.getCurrentUrl().contains("dashboard") ||
                !driver.getPageSource().contains("minimal");

        Assertions.assertTrue(loginBerhasilAtauInputValid, "Sistem menolak input batas minimum password valid!");
    }
}