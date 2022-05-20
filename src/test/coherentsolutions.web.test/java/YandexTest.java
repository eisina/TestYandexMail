import Locators.Locators;
import Managers.WebDriverManager;
import Utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static Managers.WebDriverManager.quitDriver;
import static Utils.PauseUtils.pause;

public class YandexTest {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger();;
    private PropertiesUtils propertiesUtils;

    @BeforeClass
    public void addSettings() throws FileNotFoundException {
        driver = WebDriverManager.initDriver();
        propertiesUtils = new PropertiesUtils();
    }

    @Test(description = "Checking login with valid credentials")
    public void loginTest() throws InterruptedException, IOException {
        driver.get("https://mail.yandex.com/");
        Assert.assertTrue(driver.findElement(Locators.START_PAGE).isDisplayed(), "Start Page is not displayed");
        log.info("Start Page opened");

        driver.findElement(Locators.LOGIN_BUTTON).click();
        Assert.assertTrue(driver.findElement(Locators.LOGIN_PAGE).isDisplayed(), "Login Page is not displayed");
        log.info("Login Page opened");

        driver.findElement(Locators.LOGIN_FIELD).sendKeys(propertiesUtils.getProperty("user"));
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(1);
        WebElement passwordField = driver.findElement(Locators.PASSWORD_FIELD);
        Assert.assertTrue(passwordField.isDisplayed(), "Password Field is not displayed");
        log.info("Password Field appeared");

        passwordField.sendKeys(propertiesUtils.getProperty("password"));
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(3);
        Assert.assertTrue(driver.findElement(Locators.MESSAGE_BLOCK).isDisplayed(), "Messages Page is not displayed, Login failed.");
        log.info("User logged in successfully");
    }

    @AfterClass
    public void quit() {
        quitDriver();
    }
}
