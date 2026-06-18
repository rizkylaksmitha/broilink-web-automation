package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ManagementKandangPage extends BasePage {

    private By ddKandang = By.xpath("(//select)[1]");
    private By ddPeternak = By.xpath("(//select)[2]");
    private By btnSimpanPenugasan = By.xpath("//button[normalize-space()='Simpan Penugasan']");
    private By btnSimpanLuas = By.xpath("//button[normalize-space()='Simpan Luas']");
    private By btnSimpanJumlah = By.xpath("//button[normalize-space()='Simpan Jumlah']");

    private String notifikasiTerakhir = "";

    private By alertNotifikasi = By.xpath(
            "//*[@role='alert'] | " +
                    "//div[contains(@class,'toast')] | " +
                    "//div[contains(@class,'Toastify__toast')] | " +
                    "//div[contains(@class,'alert')] | " +
                    "//div[contains(@class,'notification')] | " +
                    "//div[contains(@class,'snackbar')]"
    );

    public ManagementKandangPage(WebDriver driver) {
        super(driver);
    }

    private void isiInputJS(WebElement el, String nilai) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "setter.call(arguments[0], arguments[1]);" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                el, nilai
        );
    }

    private WebElement getInputVisible(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='number']")
        ));
        List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='number']"));
        if (inputs.size() <= index) {
            inputs = driver.findElements(By.xpath("//input[not(@type='hidden')]"));
        }
        return inputs.get(index);
    }

    // Tangkap notifikasi segera setelah tombol diklik
    private String tangkapNotifikasi() {
        try {
            WebDriverWait waitCepat = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement notif = waitCepat.until(
                    ExpectedConditions.visibilityOfElementLocated(alertNotifikasi)
            );
            String teks = notif.getText().trim();
            notifikasiTerakhir = teks; // simpan ke variabel
            return teks;
        } catch (Exception e) {
            return notifikasiTerakhir; // kembalikan yang terakhir tersimpan
        }
    }

    public void selectKandang(String kandang) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(ddKandang));
        new Select(el).selectByVisibleText(kandang.trim());
    }

    public void selectPeternak(String peternak) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(ddPeternak));
        new Select(el).selectByVisibleText(peternak.trim());
    }

    public void klikSimpanPenugasan() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSimpanPenugasan)).click();
    }

    public void fillDanSimpanLuas(String luas) {
        WebElement inputLuas = getInputVisible(0);
        isiInputJS(inputLuas, luas);
        wait.until(ExpectedConditions.elementToBeClickable(btnSimpanLuas)).click();
        // Langsung tangkap notifikasi setelah klik
        tangkapNotifikasi();
    }

    public void fillDanSimpanAyam(String ayam) {
        WebElement inputAyam = getInputVisible(1);
        isiInputJS(inputAyam, ayam);
        wait.until(ExpectedConditions.elementToBeClickable(btnSimpanJumlah)).click();
        // Langsung tangkap notifikasi setelah klik
        tangkapNotifikasi();
    }

    public void fillKandangForm(String luas, String ayam) {
        fillDanSimpanLuas(luas);
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        fillDanSimpanAyam(ayam);
    }

    public void klikSimpanKandang() {}

    // Kembalikan notifikasi terakhir yang sudah tersimpan
    public String getNotifikasiTeks() {
        if (notifikasiTerakhir.isEmpty()) {
            tangkapNotifikasi();
        }
        return notifikasiTerakhir;
    }
}