package core;

import org.openqa.selenium.By;

public class Locator {
    public static final String BASE_URL = "http://localhost:5173";

    public static final By FIELD_USERNAME = By.id("username");
    public static final By FIELD_PASSWORD = By.id("password");

    public static final By LOGIN_BUTTON = By.cssSelector(".login-button");
    public static final By ERROR_MESSAGE = By.cssSelector(".login-error");

    public final By INPUT_PAKAN = By.xpath("//h3[text()='Laporan Pakan Harian']/following-sibling::input");
    public final By INPUT_MINUM = By.xpath("//h3[text()='Laporan Minum Harian']/following-sibling::input");
    public final By INPUT_BOBOT = By.xpath("//h3[text()='Laporan Sampling Bobot']/following-sibling::input");
    public final By INPUT_KEMATIAN = By.xpath("//h3[text()='Tingkat Kematian']/following-sibling::input");
    public final By TOMBOL_KIRIM = By.xpath("//button[@type='submit']");

    public final By IHK_SUCCESS = By.xpath("//span[text()='Laporan Berhasil Terkirim']");
}