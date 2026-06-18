package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class IoTConfigurationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public IoTConfigurationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void pilihDropdownKandang() {
        try {
            WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//select | //button[contains(@id,'dropdown') or contains(@class,'select')]")
            ));
            selectElement.click();

            if (selectElement.getTagName().equals("select")) {
                org.openqa.selenium.support.ui.Select sel = new org.openqa.selenium.support.ui.Select(selectElement);
                if (sel.getOptions().size() > 1) {
                    sel.selectByIndex(1);
                }
            } else {
                WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//ul/li[1] | //div[contains(@class,'item') or contains(@class,'option')][1]")
                ));
                firstItem.click();
            }
            System.out.println("LOG SUCCESS: Berhasil memilih opsi kandang.");
        } catch (Exception e) {
            System.out.println("LOG ERROR: Dropdown kandang gagal diinteraksi. Detail: " + e.getMessage());
        }
    }

    public void isiFormSensor(String suhu, String kelembapan) {
        try {
            java.util.List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='number' or @type='text']"));

            if (inputs.size() >= 2) {
                // Input pertama (Suhu)
                inputs.get(0).clear();
                if (suhu != null && !suhu.isEmpty()) inputs.get(0).sendKeys(suhu);

                // Input kedua (Kelembapan)
                inputs.get(1).clear();
                if (kelembapan != null && !kelembapan.isEmpty()) inputs.get(1).sendKeys(kelembapan);

                System.out.println("LOG SUCCESS: Mengisi form via Index -> Suhu: " + suhu + ", Kelembapan: " + kelembapan);
            } else {
                WebElement txtSuhu = driver.findElement(By.xpath("//input[contains(@placeholder,'Suhu') or contains(@placeholder,'Temp')]"));
                txtSuhu.clear();
                if (suhu != null && !suhu.isEmpty()) txtSuhu.sendKeys(suhu);

                WebElement txtHumid = driver.findElement(By.xpath("//input[contains(@placeholder,'Kelembapan') or contains(@placeholder,'Humid')]"));
                txtHumid.clear();
                if (kelembapan != null && !kelembapan.isEmpty()) txtHumid.sendKeys(kelembapan);

                System.out.println("LOG SUCCESS: Mengisi form via Placeholder -> Suhu: " + suhu + ", Kelembapan: " + kelembapan);
            }
        } catch (Exception e) {
            System.out.println("LOG ERROR: Gagal mengisi kolom input sensor. Detail: " + e.getMessage());
        }
    }
}