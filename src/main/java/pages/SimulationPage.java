package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SimulationPage extends BasePage {

    private By ddKandang = By.xpath("//select");
    private By sliderSuhu = By.xpath("(//input[@type='range'])[1]");
    private By sliderKelembapan = By.xpath("(//input[@type='range'])[2]");
    private By sliderAmonia = By.xpath("(//input[@type='range'])[3]");
    private By btnKirim = By.xpath("//button[contains(.,'KIRIM DATA SEKARANG') or contains(text(),'KIRIM')]");

    private By alertNotifikasi = By.xpath(
            "//*[@role='alert'] | " +
                    "//div[contains(@class,'toast')] | " +
                    "//div[contains(@class,'Toastify__toast')] | " +
                    "//div[contains(@class,'alert')] | " +
                    "//div[contains(@class,'notification')] | " +
                    "//div[contains(@class,'snackbar')]"
    );

    public SimulationPage(WebDriver driver) {
        super(driver);
    }

    public void goToSimulationPage() {
        driver.get("http://localhost:5173/simulation");
    }

    public void selectKandang(String kandang) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ddKandang));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(kandang);
    }

    private void setSliderValueJS(WebElement slider, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "setter.call(arguments[0], arguments[1]);" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                slider, value
        );
    }

    public void setSuhu(String suhu) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderSuhu));
        setSliderValueJS(slider, suhu);
    }

    public void setKelembapan(String kelembapan) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderKelembapan));
        setSliderValueJS(slider, kelembapan);
    }

    public void setAmonia(String amonia) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderAmonia));
        setSliderValueJS(slider, amonia);
    }

    public void clickKirim() {
        wait.until(ExpectedConditions.elementToBeClickable(btnKirim)).click();
    }

    public String getNotifikasiTeks() {
        try {
            WebDriverWait waitCepat = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement notif = waitCepat.until(ExpectedConditions.visibilityOfElementLocated(alertNotifikasi));
            return notif.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
