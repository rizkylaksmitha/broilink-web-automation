package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class MonitoringPage extends BasePage {

    // Locators untuk Card Vital Conditions
    private By txtSuhuAktual = By.xpath(
            "//*[contains(text(), 'Suhu Aktual')]/following-sibling::* | " +
                    "//div[contains(., 'Suhu Aktual')]//*[contains(text(), '°C')] | " +
                    "//p[contains(text(), 'Suhu')]/following-sibling::*[contains(text(), '°C')]"
    );

    private By txtKelembapanAktual = By.xpath(
            "//*[contains(text(), 'Kelembapan Aktual')]/following-sibling::* | " +
                    "//div[contains(., 'Kelembapan Aktual')]//*[contains(text(), '%')] | " +
                    "//p[contains(text(), 'Kelembapan')]/following-sibling::*[contains(text(), '%')]"
    );

    private By txtAmonia = By.xpath(
            "//*[contains(text(), 'Kadar Amonia')]/following-sibling::* | " +
                    "//div[contains(., 'Kadar Amonia')]//*[contains(text(), 'ppm')] | " +
                    "//p[contains(text(), 'Amonia')]/following-sibling::*[contains(text(), 'ppm')]"
    );

    private By txtStatusKandang = By.xpath(
            "//*[contains(text(), 'Status Kandang')]/following-sibling::* | " +
                    "//div[contains(., 'Status Kandang')]//*[contains(@class, 'badge') or contains(@class, 'label') or contains(text(), 'Normal') or contains(text(), 'Waspada') or contains(text(), 'Bahaya')]"
    );

    // Locators untuk Dropdown Grafik & Canvas
    private By ddData1 = By.xpath("(//select)[1]");
    private By ddData2 = By.xpath("(//select)[2]");
    private By ddJangkaWaktu = By.xpath("(//select)[3]");
    private By ddPilihKandang = By.xpath("(//select)[4]");
    private By canvasChart = By.xpath("//canvas | //div[contains(@class, 'chart')] | //svg");

    public MonitoringPage(WebDriver driver) {
        super(driver);
    }

    public void goToMonitoringPage() {
        driver.get("http://localhost:5173/owner/monitoring");
    }

    public String getSuhuAktual() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtSuhuAktual));
        return element.getText().trim();
    }

    public String getKelembapanAktual() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtKelembapanAktual));
        return element.getText().trim();
    }

    public String getKadarAmonia() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtAmonia));
        return element.getText().trim();
    }

    public String getStatusKandang() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtStatusKandang));
        return element.getText().trim();
    }

    public void selectJangkaWaktu(String jangkaWaktu) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ddJangkaWaktu));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(jangkaWaktu);
    }

    public boolean isChartDisplayed() {
        try {
            WebElement chart = wait.until(ExpectedConditions.visibilityOfElementLocated(canvasChart));
            return chart.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
