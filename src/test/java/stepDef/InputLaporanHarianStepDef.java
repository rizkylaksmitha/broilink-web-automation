package stepDef;

import core.Locator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert; // 1. MENGGUNAKAN IMPORT JUNIT 4 (Bukan jupiter)
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

        // Ambil driver, lalu langsung maximize di sini
        DriverManager.getDriver().manage().window().maximize();

        DriverManager.getDriver().get("http://localhost:5173/login");
        this.loginPage = new LoginPage(DriverManager.getDriver());
        this.ihkPage = new InputHasilKerjaPage(DriverManager.getDriver());
    }

    @Given("user dalam keadaaan terautentikasi sebagai peternak")
    public void userLogin(){
        String currentUrl = ihkPage.getCurrentUrl();

        if (currentUrl.equals(Locator.BASE_URL + "/peternak/input")) {
            System.out.println("LOG: User sudah dalam keadaan login.");
        } else {
            System.out.println("LOG: User belum login. Memulai proses autentikasi.");
            loginPage.inputUsername("ahmad.fauzi");
            loginPage.inputPassword("password");
            loginPage.clickLoginButton();

            // 🔥 KUNCI PERBAIKAN: Berikan jeda agar token/session login sempat disimpan oleh aplikasi
            try {
                System.out.println("LOG: Menunggu session login tersimpan...");
                loginPage.waitForSuccessfulLogin(); // Menggunakan metode tunggu 3 detik dari LoginPage bawaan temanmu
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Arahkan ke halaman formulir setelah dipastikan berstatus 'Logged In'
            System.out.println("LOG: Mengarahkan ke halaman input laporan.");
            ihkPage.goToThePage();

            // Tambahan eksplisit: Tunggu sampai minimal salah satu kolom input benar-benar muncul di layar
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
        System.out.println("LOG: Memvalidasi kemunculan pop-up sukses.");

        if (statusDiharapkan.equalsIgnoreCase("success")) {
            boolean isDisplayed = ihkPage.isSuccessNotificationDisplayed();

            Assert.assertTrue("Gagal: Pop-up sukses tidak muncul atau hilang terlalu cepat!", isDisplayed);
        }
    }
    @After
    public void tearDown() {
        System.out.println("LOG: Skenario selesai. Menutup browser.");
        // Panggil method quit() bawaan BasePage via ihkPage atau loginPage
        if (ihkPage != null) {
            ihkPage.quit();
        }
    }
}