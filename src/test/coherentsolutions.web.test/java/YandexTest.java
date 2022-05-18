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
        WebElement loginButton = driver.findElement(Locators.LOGIN_BUTTON);
        Assert.assertTrue(loginButton.isDisplayed(), "Start Page is not displayed");

        loginButton.click();
        WebElement loginField = driver.findElement(Locators.LOGIN_FIELD);
        Assert.assertTrue(loginField.isDisplayed(), "Login Page is not displayed");

        loginField.sendKeys(LOGIN);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(1);
        WebElement passwordField = driver.findElement(Locators.PASSWORD_FIELD);
        Assert.assertTrue(passwordField.isDisplayed(), "Page with Password Field is not displayed");

        passwordField.sendKeys(PASSWORD);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(3);
        Assert.assertTrue(driver.findElement(Locators.MESSAGEs_BLOCK).isDisplayed(), "Messages Page is not displayed, Login failed.");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
