package stepDef;

import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.PeternakProfilePage;
import core.Locator;
import java.time.Duration;

public class EditProfileStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private PeternakProfilePage profilePage;
    private Locator locator;

    public EditProfileStepDef() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(this.driver);
        this.profilePage = new PeternakProfilePage(this.driver);
        this.locator = new Locator();
    }

    @Given("user dalam keadaan terautentikasi sebagai peternak dan berada di halaman peternak profile")
    public void userDalamKeadaanTerautentikasiSebagaiPeternakDanBeradaDiHalamanPeternakProfile() {
        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.contains("/peternak/profile")) {
            System.out.println("LOG: User sudah di halaman profil peternak.");
        } else {
            System.out.println("LOG: Mengarahkan ke halaman login...");
            driver.get(Locator.BASE_URL + "/login");
            loginPage.inputUsername("ahmad.fauzi");
            loginPage.inputPassword("password");
            loginPage.klikMasuk();

            System.out.println("LOG: Mengarahkan ke halaman profil peternak.");
            profilePage.goToThePage();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator.INPUT_EDIT_NO_WA));
                System.out.println("LOG: Halaman profil peternak berhasil dimuat.");
            } catch (Exception e) {
                System.out.println("LOG WARNING: Input WA tidak terlihat. Gagal redirect ke halaman profil.");
            }
        }
    }

    @When("User mengisi data edit profile")
    public void userMengisiDataEditProfile() {
        System.out.println("LOG: Mengisi data profil peternak.");
        profilePage.fillProfileData("081234567890", "dirubah@example.com");
    }

    @And("User menekan tombol simpan")
    public void userMenekanTombolSimpan() {
        System.out.println("LOG: Mengklik tombol Simpan.");
        profilePage.clickSave();
    }

    @Then("muncul pop up berhasil")
    public void munculPopUpBerhasil() {
        System.out.println("LOG: Memvalidasi kemunculan pop-up sukses.");
        boolean isSuccess = profilePage.isSuccessPopupDisplayed();
        Assertions.assertTrue(isSuccess, "Gagal: Pop-up berhasil update profile tidak muncul!");
    }

    @When("User mengosongkan data email pada edit profile")
    public void userMengosongkanDataEmailPadaEditProfile() {
        System.out.println("LOG: Mengosongkan data email pada profil peternak.");
        profilePage.fillProfileData("081234567890", "");
    }

    @Then("muncul pop up error {string}")
    public void munculPopUpError(String expectedErrorMessage) {
        System.out.println("LOG: Memvalidasi kemunculan pop-up error: " + expectedErrorMessage);
        String actualMessage = profilePage.getAlertText();
        Assertions.assertEquals(expectedErrorMessage, actualMessage, "Gagal: Pesan error alert tidak sesuai!");
    }
}
