package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Managers.WebDriverManager.initDriver;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage() {
        this.driver = initDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
