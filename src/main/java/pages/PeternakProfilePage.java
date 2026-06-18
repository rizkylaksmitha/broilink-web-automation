package pages;

import core.Locator;
import org.openqa.selenium.WebDriver;

public class PeternakProfilePage extends BasePage {
    private final Locator locator;

    public PeternakProfilePage(WebDriver driver) {
        super(driver);
        this.locator = new Locator();
    }

    public void goToThePage() {
        driver.get(Locator.BASE_URL + "/peternak/profile");
    }

    public void fillProfileData(String waNumber, String email) {
        writeText(locator.INPUT_EDIT_NO_WA, waNumber);
        writeText(locator.INPUT_EDIT_EMAIL, email);
    }

    public void clickSave() {
        click(locator.BUTTON_SIMPAN_PROFILE);
    }

    public boolean isSuccessPopupDisplayed() {
        return isDisplayed(locator.PROFILE_SUCCESS_POPUP);
    }

    public String getAlertText() {
        org.openqa.selenium.Alert alert = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
