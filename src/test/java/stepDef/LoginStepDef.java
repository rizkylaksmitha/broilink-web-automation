package stepDef;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.LoginPage;
import utils.DriverManager;

public class LoginStepDef {

    LoginPage loginPage;

    @Given("user berada di halaman login")
    public void user_berada_di_halaman_login() {

        DriverManager.getDriver()
                .get("http://localhost:5173/login");

        loginPage =
                new LoginPage(DriverManager.getDriver());
    }

    @When("user mengisi username {string}")
    public void user_mengisi_username(String username) {

        loginPage.inputUsername(username);
    }

    @And("user mengisi password {string}")
    public void user_mengisi_password(String password) {

        loginPage.inputPassword(password);
    }

    @And("user klik tombol login")
    public void user_klik_tombol_login() {

        loginPage.clickLoginButton();
    }

    @Then("user berhasil masuk dashboard")
    public void user_berhasil_masuk_dashboard() {

        String url =
                DriverManager.getDriver().getCurrentUrl();

        Assert.assertTrue(
                url.contains("dashboard")
        );
    }

    @Then("muncul pesan login gagal")
    public void muncul_pesan_login_gagal() {

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed()
        );
    }
}