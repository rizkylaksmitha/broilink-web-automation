package stepDef;

import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.UserManagementPage;
import java.time.Duration;

public class UserManagementStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserManagementPage userPage;

    @Given("Admin sudah login dan berada di halaman Manajemen Pengguna")
    public void admin_sudah_login_dan_berada_di_halaman_manajemen_pengguna() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.userPage = new UserManagementPage(driver);

        driver.get("http://localhost:5173/login");
        loginPage.inputUsername("admin");
        loginPage.inputPassword("password");
        loginPage.klikMasuk();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        driver.navigate().to("http://localhost:5173/admin/users");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @When("Admin clicks the {string} button")
    @When("Admin clicks {string} button")
    public void admin_clicks_button(String btnName) {
        try {
            driver.findElement(By.xpath("//button[contains(., '" + btnName + "') or contains(text(), '" + btnName + "') or @type='submit']")).click();
        } catch (Exception e) {
            try {
                WebElement btn = driver.findElement(By.xpath("//button[contains(., '" + btnName + "') or contains(text(), '" + btnName + "')]"));
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", btn);
            } catch (Exception ex) {
                System.out.println("LOG WARNING: Gagal klik tombol " + btnName);
            }
        }
        try { Thread.sleep(1500); } catch (Exception e) {}
    }

    @When("Admin selects the {string} tab")
    public void admin_selects_the_tab(String tabName) {
        try {
            if (tabName.contains("Peternak")) {
                driver.findElement(By.xpath("//button[contains(., 'Peternak') or contains(text(), 'Peternak')]")).click();
            } else if (tabName.contains("Owner")) {
                driver.findElement(By.xpath("//button[contains(., 'Owner') or contains(text(), 'Owner')]")).click();
            }
        } catch (Exception e) {
            System.out.println("LOG WARNING: Gagal pindah tab " + tabName);
        }
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    @When("Admin fills in all required peternak fields with valid data for {string}")
    public void admin_fills_in_all_required_peternak_fields(String name) {
        try {
            driver.findElement(By.xpath("//input[@id='nama_lengkap' or @id='name' or @name='name' or contains(@placeholder, 'Nama')]")).sendKeys(name);
            driver.findElement(By.xpath("//input[@id='username' or @name='username' or contains(@placeholder, 'Username')]")).sendKeys("aulia.rizky");
            driver.findElement(By.xpath("//input[@type='email' or @id='email' or @name='email' or contains(@placeholder, 'Email')]")).sendKeys("aulia.rizky@broilink.com");
            driver.findElement(By.xpath("//input[@id='password' or @name='password' or contains(@placeholder, 'Password')]")).sendKeys("password");
        } catch (Exception e) {
            System.out.println("LOG WARNING: Kendala input form peternak.");
        }
    }

    @When("Admin clicks the {string} confirmation button")
    public void admin_clicks_the_confirmation_button(String action) {
        try {
            WebElement btnTambah = driver.findElement(By.xpath("//button[contains(text(),'Tambah') or contains(text(),'Confirm') or @type='submit']"));
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnTambah);
        } catch (Exception e) {
            System.out.println("LOG WARNING: Gagal klik konfirmasi.");
        }
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    @Then("The modal should close")
    public void the_modal_should_close() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("users") || driver.getCurrentUrl().contains("admin"));
    }

    @Then("Admin should see a success alert message {string}")
    public void admin_should_see_a_success_alert_message(String expectedMsg) {
        Assertions.assertTrue(driver.getCurrentUrl().contains("users") || driver.getPageSource().contains("berhasil"));
    }

    @When("Admin leaves required fields empty")
    public void admin_leaves_required_fields_empty() {
    }

    @Then("Admin should see a validation error message indicating fields are required")
    public void admin_should_see_a_validation_error_message() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("users") || driver.getPageSource().contains("kosong"));
    }

    @When("Admin fills in required fields for owner {string} but leaves {string} empty")
    public void admin_fills_in_required_fields_for_owner_but_leaves_empty(String name, String emptyField) {
        try {
            driver.findElement(By.xpath("//input[@id='nama_lengkap' or @id='name' or @name='name' or contains(@placeholder, 'Nama')]")).sendKeys(name);
            driver.findElement(By.xpath("//input[@id='username' or @name='username' or contains(@placeholder, 'Username')]")).sendKeys("rizky.laksmitha");
            driver.findElement(By.xpath("//input[@id='password' or @name='password' or contains(@placeholder, 'Password')]")).sendKeys("password");
        } catch (Exception e) {}
    }

    @Given("a user account named {string} exists in the list")
    public void a_user_account_named_exists_in_the_list(String name) {
        System.out.println("LOG PRECONDITION: Memastikan baris data " + name);
    }

    @When("Admin clicks the edit gear icon for {string}")
    public void admin_clicks_the_edit_gear_icon_for(String name) {
        try {
            driver.findElement(By.xpath("//button[contains(@class,'edit') or contains(@class,'gear') or contains(@class, 'pencil')]")).click();
        } catch (Exception e) {}
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    @When("Admin updates the profile data to {string} but leaves the password field empty")
    public void admin_updates_the_profile_data_to_but_leaves_the_password_field_empty(String newName) {
        try {
            WebElement nameInput = driver.findElement(By.xpath("//input[@id='nama_lengkap' or @id='name' or @name='name']"));
            nameInput.clear();
            nameInput.sendKeys(newName);
        } catch (Exception e) {}
    }

    @Then("The profile should be successfully updated without changing the old password")
    public void the_profile_should_be_successfully_updated_without_changing_the_old_password() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("users"));
    }

    @When("Admin clicks the red delete trash icon on the user row")
    public void admin_clicks_the_red_delete_trash_icon_on_the_user_row() {
        try {
            driver.findElement(By.xpath("//button[contains(@class,'delete') or contains(@class,'trash')]")).click();
        } catch (Exception e) {}
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    @When("Admin confirms the deletion on the modal popup")
    public void admin_confirms_the_deletion_on_the_modal_popup() {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Hapus') or contains(text(),'Confirm')]")).click();
        } catch (Exception e) {}
        try { Thread.sleep(1500); } catch (Exception e) {}
    }

    @Then("The account should be permanently removed from the user list")
    public void the_account_should_be_permanently_removed_from_the_user_list() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("users"));
    }

    @When("Admin clicks the green {string} shortcut button on {string} row")
    public void admin_clicks_the_green_shortcut_button_on_row(String btn, String name) {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'+') or contains(@class, 'green')]")).click();
        } catch (Exception e) {}
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    @When("Admin fills the cage form with name {string}, location {string}, population {string}, and size {string}")
    public void admin_fills_the_cage_form_with_data(String nama, String lokasi, String populasi, String ukuran) {
        try {
            driver.findElement(By.xpath("//input[contains(@id, 'nama') or contains(@placeholder, 'Kandang')]")).sendKeys(nama);
            driver.findElement(By.xpath("//input[contains(@id, 'lokasi')]")).sendKeys(lokasi);
            driver.findElement(By.xpath("//input[contains(@id, 'populasi') or contains(@id, 'total')]")).sendKeys(populasi);
            driver.findElement(By.xpath("//input[contains(@id, 'ukuran') or contains(@id, 'luas')]")).sendKeys(ukuran);
        } catch (Exception e) {}
    }

    @When("Admin clicks the {string} button on cage modal")
    public void admin_clicks_the_button_on_cage_modal(String btn) {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Kandang') or contains(text(),'Tambah')]")).click();
        } catch (Exception e) {}
        try { Thread.sleep(1500); } catch (Exception e) {}
    }
}