package stepDef;

import core.Locator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InputHasilKerjaPage;
import pages.LoginPage;
import utils.DriverManager;

import java.time.Duration;
import java.util.Map;

public class InputLaporanHarianStepDef {

    private LoginPage loginPage;
    private InputHasilKerjaPage ihkPage;
    private Locator locator;

    @Before
    public void setUp(){
        this.locator = new Locator();
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get("http://localhost:5173/login");
        this.loginPage = new LoginPage(DriverManager.getDriver());
        this.ihkPage = new InputHasilKerjaPage(DriverManager.getDriver());
    }

    @Given("user dalam keadaaan terautentikasi sebagai peternak")
    public void userLogin(){
        String currentUrl = DriverManager.getDriver().getCurrentUrl();

        if (currentUrl.equals(Locator.BASE_URL + "/peternak/input")) {
            System.out.println("LOG: User sudah dalam keadaan login.");
        } else {
            System.out.println("LOG: User belum login. Memulai proses autentikasi.");
            loginPage.inputUsername("ahmad.fauzi");
            loginPage.inputPassword("password");

            System.out.println("LOG: Mengklik tombol login...");
            try {
                DriverManager.getDriver().findElement(By.xpath("//button[@type='submit' or contains(., 'Login')]")).click();
            } catch (Exception e) {
                DriverManager.getDriver().findElement(By.id("password")).submit();
            }

            try {
                System.out.println("LOG: Menunggu session login tersimpan...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("LOG: Mengarahkan ke halaman input laporan.");
            ihkPage.goToThePage();

            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator.INPUT_PAKAN));
                System.out.println("LOG: Halaman formulir berhasil dimuat sepenuhnya.");
            } catch (Exception e) {
                System.out.println("LOG WARNING: Elemen formulir belum terlihat. Ada kemungkinan gagal redirect.");
            }
        }
    }

    @When("user mengisi formulir laporan harian dengan data berikut:")
    public void userMengisiFormulirLaporanHarian(DataTable dataTable) {
        System.out.println("LOG: Membaca data dari tabel Examples Gherkin.");
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String pakan = data.get("pakan");
        String minum = data.get("minum");
        String bobot = data.get("bobot");
        String kematian = data.get("kematian");
        ihkPage.isiLaporanHarian(pakan, minum, bobot, kematian);
    }

    @And("user menekan tombol kirim laporan")
    public void userMenekanTombolKirimLaporan() {
        System.out.println("LOG: Mengklik tombol Kirim.");
        ihkPage.klikKirim();
    }

    @Then("muncul pop up notifikasi dengan status {string}")
    public void munculPopUpNotifikasiDenganStatus(String statusDiharapkan) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3));

        if ("success".equalsIgnoreCase(statusDiharapkan)) {
            System.out.println("LOG: Memvalidasi kemunculan pop-up sukses.");
            boolean isSuccess = ihkPage.isSuccessNotificationDisplayed();
            Assertions.assertTrue(isSuccess, "Gagal: Pop-up sukses tidak muncul!");
        } else if ("failed".equalsIgnoreCase(statusDiharapkan)) {
            System.out.println("LOG: Memvalidasi kemunculan browser alert error.");
            try {
                org.openqa.selenium.Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("LOG ALERT: " + alert.getText());
                alert.accept();
            } catch (Exception e) {
                Assertions.fail("Gagal: Browser alert validation error tidak muncul!");
            }
        } else if ("invalid_form".equalsIgnoreCase(statusDiharapkan)) {
            System.out.println("LOG: Memvalidasi bahwa HTML5 validation memblokir form (field kosong).");
            boolean isInvalid = ihkPage.isFormHtml5Invalid();
            Assertions.assertTrue(isInvalid, "Gagal: Form harusnya invalid karena ada field kosong!");
        } else {
            Assertions.fail("Status tidak dikenali: " + statusDiharapkan);
        }
    }

    @org.junit.After
    public void tearDown() {
        System.out.println("LOG: Skenario selesai.");
    }
}