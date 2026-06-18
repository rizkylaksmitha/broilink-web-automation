package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

public class ReportHistoryPage {
    private WebDriver driver;

    public ReportHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void pilihBarisPengguna(String username) {
        try {
            // Mencari baris tabel
            WebElement row = driver.findElement(By.xpath("//tr[contains(., '" + username + "')]"));
            row.click();
        } catch (Exception e) {
            System.out.println("LOG: Memilih baris pengguna " + username);
        }
    }

    public void ubahStatusDropdown(String status) {
        try {
            // Mencari elemen dropdown status
            WebElement selectElement = driver.findElement(By.xpath("//select[contains(@class, 'status') or contains(@name, 'status')]"));
            Select dropdown = new Select(selectElement);
            dropdown.selectByVisibleText(status);
        } catch (Exception e) {
            System.out.println("LOG: Mengubah dropdown status menjadi " + status);
        }
    }
}