package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OwnerDashboardPage extends BasePage {

    // Locators untuk card Kondisi Kandang di Dashboard Owner
    private By cardKondisiKandang = By.xpath("//div[contains(.,'Kondisi Kandang') or contains(.,'Kondisi')]");
    private By txtKandangName = By.xpath("//div[contains(.,'Kondisi Kandang')]//p[contains(text(),'Kandang')] | //div[contains(.,'Kondisi Kandang')]//*[contains(text(),'Sleman')]");
    private By txtStatusKandang = By.xpath("//div[contains(.,'Kondisi Kandang')]//*[contains(text(),'Waspada') or contains(text(),'Normal') or contains(text(),'Bahaya') or contains(text(),'Aman')]");
    private By txtSuhuKandang = By.xpath("//div[contains(.,'Kondisi Kandang')]//*[contains(text(),'°C')]");

    // Locators untuk bagian Aktivitas Peternakan
    private By sectionAktivitas = By.xpath("//div[contains(text(),'Aktivitas Peternakan') or contains(.,'Aktivitas')]");

    public OwnerDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goToDashboard() {
        driver.get("http://localhost:5173/owner/dashboard");
    }

    public String getKandangName() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtKandangName));
        return element.getText().trim();
    }

    public String getStatusKandang() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtStatusKandang));
        return element.getText().trim();
    }

    public String getSuhuKandang() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtSuhuKandang));
        return element.getText().trim();
    }

    public boolean isAktivitasSectionDisplayed() {
        try {
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionAktivitas));
            return section.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
