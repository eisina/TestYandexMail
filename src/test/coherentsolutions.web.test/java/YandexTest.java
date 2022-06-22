import Locators.Locators;
import Managers.WebDriverManager;
import Utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import static Managers.WebDriverManager.quitDriver;
import static Utils.PauseUtils.pause;

public class YandexTest {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger();
    ;
    private PropertiesUtils propertiesUtils;
    private WebDriverWait webDriverWait;

    @BeforeClass
    public void addSettings() throws FileNotFoundException {
        driver = WebDriverManager.initDriver();
        propertiesUtils = new PropertiesUtils();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @DataProvider(name = "credentials")
    public Object[][] dpMethod() throws IOException {
        return new Object[][]{{propertiesUtils.getProperty("user2"), propertiesUtils.getProperty("password2")},
                {propertiesUtils.getProperty("user1"), propertiesUtils.getProperty("password1")}};
    }

    @Test(description = "Checking login with valid credentials", dataProvider = "credentials")
    public void loginTest(String login, String password) throws InterruptedException {
        driver.get("https://mail.yandex.com/");
        Assert.assertTrue(driver.findElement(Locators.START_PAGE).isDisplayed(), "Start Page is not displayed");
        log.info("Start Page opened");

        driver.findElement(Locators.LOGIN_BUTTON).click();
        Assert.assertTrue(driver.findElement(Locators.LOGIN_PAGE).isDisplayed(), "Login Page is not displayed");
        log.info("Login Page opened");

        driver.findElement(Locators.LOGIN_FIELD).sendKeys(login);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        pause(3); //Thread Sleep is explicit waiter as it has to wait for the full time specified as the argument
        // and it is only invoked where it is written, like explicit wait.
        webDriverWait.pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(Locators.PASSWORD_FIELD));
        WebElement passwordField = driver.findElement(Locators.PASSWORD_FIELD);
        Assert.assertTrue(passwordField.isDisplayed(), "Password Field is not displayed");
        log.info("Password Field appeared");

        passwordField.sendKeys(password);
        driver.findElement(Locators.PROCEED_BUTTON).click();
        webDriverWait.pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(Locators.USER_NAME));
        pause(3);
        Assert.assertTrue(driver.findElement(Locators.MESSAGE_BLOCK).isDisplayed(),
                "Messages Page is not displayed, Login failed.");
        log.info("User logged in successfully");

        driver.findElement(Locators.SETTING_BUTTON).click();
        driver.findElement(Locators.LOGOUT_BUTTON).click();
        Assert.assertTrue(driver.findElement(Locators.PASSWORD_FIELD).isDisplayed(),
                "Login Page is opened, user is logged out.");
        log.info("User logged out successfully");

        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void quit() {
        quitDriver();
    }
}
