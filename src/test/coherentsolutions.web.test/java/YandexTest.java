import Locators.Locators;
import Managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.PauseUtils.pause;

public class YandexTest {

    private WebDriver driver;

    private final String PASSWORD = "testik1234";
    private final String LOGIN = "katetestng";

    @BeforeMethod
    public void addSettings() {
        driver = WebDriverManager.initDriver();
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://mail.yandex.com/");
        Assert.assertTrue(driver.findElement(Locators.START_PAGE).isDisplayed(), "Start Page is not displayed");

        driver.findElement(Locators.LOGIN_BUTTON).click();
        Assert.assertTrue(driver.findElement(Locators.LOGIN_PAGE).isDisplayed(), "Login Page is not displayed");

        driver.findElement(Locators.LOGIN_FIELD).sendKeys(LOGIN);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(1);
        WebElement passwordField = driver.findElement(Locators.PASSWORD_FIELD);
        Assert.assertTrue(passwordField.isDisplayed(), "Password Field is not displayed");

        passwordField.sendKeys(PASSWORD);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(3);
        Assert.assertTrue(driver.findElement(Locators.MESSAGE_BLOCK).isDisplayed(), "Messages Page is not displayed, Login failed.");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
