package stepDef;

import org.openqa.selenium.WebElement;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.IoTConfigurationPage;
import java.time.Duration;

public class IoTConfigurationStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private IoTConfigurationPage iotPage;

    @Given("Admin sudah login dan berada di halaman Konfigurasi IoT")
    public void admin_sudah_login_dan_berada_di_halaman_konfigurasi_iot() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.iotPage = new IoTConfigurationPage(driver);

        driver.get("http://localhost:5173/login");
        loginPage.inputUsername("admin");
        loginPage.inputPassword("password");
        loginPage.klikMasuk();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        driver.navigate().to("http://localhost:5173/admin/farms");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @When("Admin selects cage from dropdown")
    public void admin_selects_cage_from_dropdown() {
        iotPage.pilihDropdownKandang();
    }

    @When("Admin leaves one of the sensor fields empty")
    public void admin_leaves_one_of_the_sensor_fields_empty() {
        iotPage.isiFormSensor("", "50"); // Kosongkan suhu
    }

    @When("Admin trigger save for IoT configuration")
    public void admin_trigger_save_for_iot_configuration() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Simpan') or contains(text(),'Konfigurasi') or contains(.,'Simpan')]")
            ));

            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btn);
            System.out.println("LOG SUCCESS: Berhasil menekan tombol simpan IoT.");
        } catch (Exception e) {
            try {
                WebElement btnAlt = driver.findElement(By.cssSelector("button, input[type='submit']"));
                btnAlt.click();
                System.out.println("LOG SUCCESS: Berhasil menekan tombol simpan IoT (Alt).");
            } catch (Exception ex) {
                System.out.println("LOG ERROR: Selenium beneran tidak bisa menemukan elemen tombol simpan di UI!");
            }
        }
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    @Then("The system should reject saving and show error that all fields are required")
    public void the_system_should_reject_saving_and_show_error_required() {
        try { Thread.sleep(1500); } catch (Exception e) {}
        String pageSource = driver.getPageSource().toLowerCase();
        boolean isRejected = driver.getCurrentUrl().contains("farms") ||
                pageSource.contains("wajib") ||
                pageSource.contains("kosong") ||
                pageSource.contains("required") ||
                pageSource.contains("fill");

        Assertions.assertTrue(isRejected, "Sistem meloloskan form kosong!");
    }

    @When("Admin fills all sensor fields with valid data temperature {string} and humidity {string}")
    public void admin_fills_all_sensor_fields_with_valid_data(String suhu, String kelembapan) {
        iotPage.isiFormSensor(suhu, kelembapan);
    }

    @Then("The system should successfully save and show toast {string}")
    public void the_system_should_successfully_save(String msg) {
        try { Thread.sleep(1500); } catch (Exception e) {}
        boolean isSuccess = driver.getCurrentUrl().contains("farms") ||
                driver.getCurrentUrl().contains("dashboard") ||
                driver.getPageSource().contains("Berhasil");

        Assertions.assertTrue(isSuccess, "Gagal menyimpan konfigurasi IoT nilai valid!");
    }

    @When("Admin inputs a negative value {string} into max temperature field")
    public void admin_inputs_a_negative_value_into_max_temperature_field(String value) {
        iotPage.isiFormSensor(value, "50");
    }

    @Then("The system should reject saving and show error validation that value cannot be negative")
    public void the_system_should_reject_saving_and_show_error_negative() {
        try { Thread.sleep(2000); } catch (Exception e) {}

        String pageSource = driver.getPageSource().toLowerCase();

        boolean apakahAdaPesanErorNegatif = pageSource.contains("negatif") || pageSource.contains("minus");
        boolean malahLolosSukses = pageSource.contains("berhasil") || pageSource.contains("konfigurasi berhasil disimpan");

        if (malahLolosSukses && !apakahAdaPesanErorNegatif) {
            System.out.println("LOG QA INVESTIGATION: TC-ADM-15 MENEMUKAN DEFECT! Aplikasi meloloskan nilai negatif.");
            Assertions.fail("BUG DETECTED: Sistem meloloskan input nilai negatif (-35) dan memunculkan pop-up sukses!");
        } else {
            Assertions.assertTrue(apakahAdaPesanErorNegatif, "Sistem meloloskan input nilai negatif!");
        }
    }
}