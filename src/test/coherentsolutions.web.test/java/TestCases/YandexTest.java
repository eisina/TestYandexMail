package TestCases;

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
import java.time.Duration;

import static Managers.WebDriverManager.initDriver;
import static Managers.WebDriverManager.quitDriver;
import static Pages.BasePage.takeSnapShot;
import static Utils.ConstantUtils.YANDEX_LOGIN_PAGE_URL;
import static java.time.LocalTime.now;

public class YandexTest {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger();
    private PropertiesUtils propertiesUtils;
    private WebDriverWait webDriverWait;

    @BeforeClass
    public void addSettings() throws FileNotFoundException {
        driver = WebDriverManager.initDriver();
        propertiesUtils = new PropertiesUtils();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(description = "Checking login and log out with valid credentials")
    public void loginTest() throws Exception {
        initDriver().get(YANDEX_LOGIN_PAGE_URL);
        StartPage startPage = new StartPage(driver, webDriverWait);
        Assert.assertTrue(startPage.isPageLoaded(), "Start Page is not displayed");
        takeSnapShot(driver, String.format("screenshots/Step1_%s.png", now()));
        log.info("Start Page opened");

        LoginPage loginPage = startPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoginFieldDisplayed(), "Login Page is not displayed");
        takeSnapShot(driver, String.format("screenshots/Step2_%s.png", now()));
        log.info("Login Page opened");

        loginPage.enterLogin(propertiesUtils.getProperty("user1"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password Field is not displayed");
        takeSnapShot(driver, String.format("screenshots/Step3_%s.png", now()));
        log.info("Password Field appeared");

        loginPage.enterPassword(propertiesUtils.getProperty("password1"));
        MailPage mailPage = loginPage.clickLogin();
        Assert.assertTrue(mailPage.isUsernameDisplay(), "Messages Page is not displayed, Login failed.");
        takeSnapShot(driver, String.format("screenshots/Step4_%s.png", now()));
        log.info("User logged in successfully");

        mailPage.clickSettings();
        mailPage.logOut();
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Login Page is opened, user is logged out.");
        takeSnapShot(driver, String.format("screenshots/Step1_%5.png", now()));
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
