package YandexTests;

import Managers.WebDriverManager;
import Pages.LoginPage;
import Pages.MailPage;
import Pages.StartPage;
import Utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static Managers.WebDriverManager.quitDriver;
import static Utils.ConstantUtils.YANDEX_LOGIN_PAGE_URL;
import static Utils.PauseUtils.pause;

public class YandexTest {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger();
    private PropertiesUtils propertiesUtils;

    @BeforeClass
    public void addSettings() throws FileNotFoundException {
        driver = WebDriverManager.initDriver();
        propertiesUtils = new PropertiesUtils();
    }

    @Test(description = "Checking login and log out with valid credentials")
    public void loginTest() throws IOException, InterruptedException {
        driver.get(YANDEX_LOGIN_PAGE_URL);
        StartPage startPage = new StartPage();
        Assert.assertTrue(startPage.pageLoaded(), "Start Page is not displayed");
        log.info("Start Page opened");

        LoginPage loginPage = startPage.clickLoginButton();
        Assert.assertTrue(loginPage.loginFieldDisplayed(), "Login Page is not displayed");
        log.info("Login Page opened");

        loginPage.enterLogin(propertiesUtils.getProperty("user1"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.passwordFieldDisplayed(), "Password Field is not displayed");
        log.info("Password Field appeared");

        loginPage.enterPassword(propertiesUtils.getProperty("password1"));
        MailPage mailPage = loginPage.clickLogin();
        Assert.assertTrue(mailPage.usernameDisplay(), "Messages Page is not displayed, Login failed.");
        log.info("User logged in successfully");

        pause(3);
        mailPage.clickSettings();
        mailPage.logOut();
        Assert.assertTrue(loginPage.passwordFieldDisplayed(), "Login Page is opened, user is logged out.");
        log.info("User logged out successfully");
    }


    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void quit() {
        quitDriver();
    }
}
