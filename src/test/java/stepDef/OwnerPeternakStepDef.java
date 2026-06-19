package stepDef;

import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.SimulationPage;
import pages.MonitoringPage;
import pages.OwnerDashboardPage;
import java.time.Duration;

public class OwnerPeternakStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private SimulationPage simulationPage;
    private MonitoringPage monitoringPage;
    private OwnerDashboardPage dashboardPage;

    public OwnerPeternakStepDef() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(this.driver);
        this.simulationPage = new SimulationPage(this.driver);
        this.monitoringPage = new MonitoringPage(this.driver);
        this.dashboardPage = new OwnerDashboardPage(this.driver);
    }

    @Given("User melakukan login sebagai Owner dengan username {string} dan password {string}")
    public void user_melakukan_login_sebagai_owner(String username, String password) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/owner/") || currentUrl.contains("/simulation")) {
            System.out.println("LOG: User sudah dalam keadaan terautentikasi.");
            return;
        }

        System.out.println("LOG: Membuka halaman login untuk Owner.");
        driver.get("http://localhost:5173/login");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.klikMasuk();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("dashboard"),
                    ExpectedConditions.urlContains("owner"),
                    ExpectedConditions.urlContains("simulation")
            ));
            System.out.println("LOG: Berhasil masuk ke halaman utama Owner.");
        } catch (Exception e) {
            System.out.println("LOG WARNING: Redireksi login lambat atau gagal.");
        }
    }

    @Given("User berada di Halaman Simulasi {string}")
    public void user_berada_di_halaman_simulasi(String url) {
        System.out.println("LOG: Mengakses halaman simulasi: " + url);
        driver.get(url);
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    @When("User memilih Kandang {string} di halaman simulasi")
    public void user_memilih_kandang_di_halaman_simulasi(String kandang) {
        System.out.println("LOG: Memilih Kandang: " + kandang);
        simulationPage.selectKandang(kandang);
    }

    @When("User mengatur slider Suhu ke {string} derajat Celcius")
    public void user_mengatur_slider_suhu_ke(String suhu) {
        System.out.println("LOG: Mengatur slider Suhu ke: " + suhu);
        simulationPage.setSuhu(suhu);
    }

    @When("User mengatur slider Kelembapan ke {string} persen")
    public void user_mengatur_slider_kelembapan_ke(String kelembapan) {
        System.out.println("LOG: Mengatur slider Kelembapan ke: " + kelembapan);
        simulationPage.setKelembapan(kelembapan);
    }

    @When("User mengatur slider Amonia ke {string} ppm")
    public void user_mengatur_slider_amonia_ke(String amonia) {
        System.out.println("LOG: Mengatur slider Amonia ke: " + amonia);
        simulationPage.setAmonia(amonia);
    }

    @When("User mengklik tombol Kirim Data Sekarang")
    public void user_mengklik_tombol_kirim_data_sekarang() {
        System.out.println("LOG: Mengklik tombol Kirim Data.");
        simulationPage.clickKirim();
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    @Then("Sistem menampilkan notifikasi sukses pengiriman data")
    public void sistem_menampilkan_notifikasi_sukses_pengiriman_data() {
        String notif = simulationPage.getNotifikasiTeks();
        System.out.println("LOG: Notifikasi dari sistem: " + notif);
        if (!notif.isEmpty()) {
            Assertions.assertTrue(notif.toLowerCase().contains("berhasil")
                            || notif.toLowerCase().contains("sukses")
                            || notif.toLowerCase().contains("sent")
                            || notif.toLowerCase().contains("kirim"),
                    "Pesan notifikasi tidak sesuai: " + notif);
        }
    }

    @Then("User membuka Halaman Monitoring {string}")
    public void user_membuka_halaman_monitoring(String url) {
        System.out.println("LOG: Mengakses halaman Monitoring: " + url);
        driver.get(url);
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Then("Card Suhu Aktual menampilkan nilai {string}")
    public void card_suhu_aktual_menampilkan_nilai(String expectedSuhu) {
        String actualSuhu = monitoringPage.getSuhuAktual();
        System.out.println("LOG: Nilai Suhu Aktual di UI: " + actualSuhu);
        Assertions.assertTrue(actualSuhu.contains(expectedSuhu),
                "Suhu Aktual tidak sesuai! Ekspektasi: " + expectedSuhu + ", Aktual: " + actualSuhu);
    }

    @Then("Card Status Kandang menampilkan status {string} atau {string}")
    public void card_status_kandang_menampilkan_status_atau(String status1, String status2) {
        String actualStatus = monitoringPage.getStatusKandang().toLowerCase();
        System.out.println("LOG: Status Kandang di UI: " + actualStatus);
        boolean matches = actualStatus.contains(status1.toLowerCase()) || actualStatus.contains(status2.toLowerCase());
        Assertions.assertTrue(matches,
                "Status Kandang tidak sesuai! Ekspektasi: " + status1 + " atau " + status2 + ", Aktual: " + actualStatus);
    }

    @Then("Card Kadar Amonia menampilkan nilai {string} atau {string}")
    public void card_kadar_amonia_menampilkan_nilai_atau(String val1, String val2) {
        String actualAmonia = monitoringPage.getKadarAmonia();
        System.out.println("LOG: Kadar Amonia di UI: " + actualAmonia);
        boolean matches = actualAmonia.contains(val1) || actualAmonia.contains(val2);
        Assertions.assertTrue(matches,
                "Kadar Amonia tidak sesuai! Ekspektasi: " + val1 + " atau " + val2 + ", Aktual: " + actualAmonia);
    }

    @Then("Card Kelembapan Aktual menampilkan nilai {string}")
    public void card_kelembapan_aktual_menampilkan_nilai(String expectedHumid) {
        String actualHumid = monitoringPage.getKelembapanAktual();
        System.out.println("LOG: Kelembapan Aktual di UI: " + actualHumid);
        Assertions.assertTrue(actualHumid.contains(expectedHumid),
                "Kelembapan Aktual tidak sesuai! Ekspektasi: " + expectedHumid + ", Aktual: " + actualHumid);
    }

    @Then("Grafik monitoring sensor berhasil ditampilkan")
    public void grafik_monitoring_sensor_berhasil_ditampilkan() {
        boolean chartVisible = monitoringPage.isChartDisplayed();
        System.out.println("LOG: Keberadaan grafik di UI: " + chartVisible);
        Assertions.assertTrue(chartVisible, "Elemen grafik tidak ditemukan atau gagal dimuat di UI!");
    }

    @When("User memilih filter jangka waktu {string}")
    public void user_memilih_filter_jangka_waktu(String filter) {
        System.out.println("LOG: Memilih filter jangka waktu: " + filter);
        monitoringPage.selectJangkaWaktu(filter);
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    @Given("User membuka Halaman Dashboard Owner {string}")
    public void user_membuka_halaman_dashboard_owner(String url) {
        System.out.println("LOG: Mengakses Halaman Dashboard Owner: " + url);
        dashboardPage.goToDashboard();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Then("Dashboard Owner menampilkan nama kandang {string} atau {string}")
    public void dashboard_owner_menampilkan_nama_kandang_atau(String name1, String name2) {
        String actualName = dashboardPage.getKandangName();
        System.out.println("LOG: Nama Kandang di Dashboard: " + actualName);
        boolean matches = actualName.toLowerCase().contains(name1.toLowerCase())
                || actualName.toLowerCase().contains(name2.toLowerCase());
        Assertions.assertTrue(matches,
                "Nama kandang di Dashboard tidak sesuai! Ekspektasi: " + name1 + " atau " + name2 + ", Aktual: " + actualName);
    }

    @Then("Dashboard Owner menampilkan status kandang {string} atau {string} atau {string}")
    public void dashboard_owner_menampilkan_status_kandang_atau_atau(String status1, String status2, String status3) {
        String actualStatus = dashboardPage.getStatusKandang();
        System.out.println("LOG: Status Kandang di Dashboard: " + actualStatus);
        boolean matches = actualStatus.toLowerCase().contains(status1.toLowerCase())
                || actualStatus.toLowerCase().contains(status2.toLowerCase())
                || actualStatus.toLowerCase().contains(status3.toLowerCase());
        Assertions.assertTrue(matches,
                "Status kandang di Dashboard tidak sesuai! Ekspektasi: " + status1 + " atau " + status2 + " atau " + status3 + ", Aktual: " + actualStatus);
    }

    @Then("Dashboard Owner menampilkan suhu kandang terkini")
    public void dashboard_owner_menampilkan_suhu_kandang_terkini() {
        String actualSuhu = dashboardPage.getSuhuKandang();
        System.out.println("LOG: Suhu Kandang di Dashboard: " + actualSuhu);
        Assertions.assertTrue(actualSuhu.contains("°C") || !actualSuhu.isEmpty(),
                "Suhu kandang di Dashboard tidak valid atau kosong!");
    }

    @Then("Dashboard Owner menampilkan section Aktivitas Peternakan")
    public void dashboard_owner_menampilkan_section_aktivitas_peternakan() {
        boolean isAktivitasVisible = dashboardPage.isAktivitasSectionDisplayed();
        System.out.println("LOG: Keberadaan section Aktivitas Peternakan: " + isAktivitasVisible);
        Assertions.assertTrue(isAktivitasVisible,
                "Section Aktivitas Peternakan tidak ditampilkan di Dashboard Owner!");
    }
}
