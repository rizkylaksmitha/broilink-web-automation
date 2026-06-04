package pages;

import core.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InputHasilKerjaPage extends BasePage {

    private final Locator locator;

    public InputHasilKerjaPage(WebDriver driver) {
        super(driver);
        this.locator = new Locator(); // Inisialisasi object locator
    }

    public void goToThePage(){
        driver.get(Locator.BASE_URL + "/peternak/input");
    }

    public void isiLaporanHarian(String pakan, String minum, String bobot, String kematian) {
        driver.findElement(locator.INPUT_PAKAN).sendKeys(pakan);
        driver.findElement(locator.INPUT_MINUM).sendKeys(minum);
        driver.findElement(locator.INPUT_BOBOT).sendKeys(bobot);
        driver.findElement(locator.INPUT_KEMATIAN).sendKeys(kematian);
    }

    public void klikKirim() {
        driver.findElement(locator.TOMBOL_KIRIM).click();
    }

    public boolean isSuccessNotificationDisplayed() {
        try {
            // 1. Naikkan waktu tunggu sedikit (misal 5 detik) untuk mengantisipasi delay server lokal
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator.IHK_SUCCESS));

            String textPopUp = element.getText().toLowerCase();
            System.out.println("LOG DEBUG: Teks pop-up yang terbaca di layar adalah -> " + element.getText());

            // 2. Gunakan potongan kata kunci universal (asal ada kata 'berhasil' atau 'sukses' atau 'terkirim')
            return textPopUp.contains("berhasil") || textPopUp.contains("sukses") || textPopUp.contains("terkirim");
        } catch (Exception e) {
            System.out.println("LOG DEBUG: Pop-up sukses tidak terdeteksi oleh Selenium dalam 5 detik.");
            return false;
        }
    }
}