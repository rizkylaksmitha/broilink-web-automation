package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    private WebDriver driver;

    // Selector elemen disesuaikan dengan struktur umum Landing Page
    private By tombolGabung = By.xpath("//button[contains(text(),'Gabung') or contains(@id,'gabung')]");
    private By heroSection = By.xpath("//section[contains(@class,'hero') or contains(@id,'hero')]");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void bukaHalamanUtama() {
        driver.get("http://localhost:5173");
    }

    public void klikTombolGabung() {
        driver.findElement(tombolGabung).click();
    }

    public boolean isHeroSectionDisplayed() {
        try {
            return driver.findElement(heroSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}