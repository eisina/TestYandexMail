package TestCases;

import Managers.WebDriverManager;
import Pages.LoginPage;
import Pages.MailPage;
import Pages.StartPage;
import Utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static Managers.WebDriverManager.initDriver;
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

    @DataProvider(name = "credentials")
    public Object[][] dpMethod() throws IOException {
        return new Object[][]{{propertiesUtils.getProperty("user2"), propertiesUtils.getProperty("password2")},
                {propertiesUtils.getProperty("user1"), propertiesUtils.getProperty("password1")}};
    }

    @Test(description = "Checking login with valid credentials", dataProvider = "credentials")
    public void loginTest1(String login, String password) throws InterruptedException {
        initDriver().get(YANDEX_LOGIN_PAGE_URL);
        StartPage startPage = new StartPage();
        Assert.assertTrue(startPage.pageLoaded(), "Start Page is not displayed");
        log.info("Start Page opened");

        LoginPage loginPage = startPage.clickLoginButton();
        Assert.assertTrue(loginPage.loginFieldDisplayed(), "Login Page is not displayed");
        log.info("Login Page opened");

        loginPage.enterLogin(login);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.passwordFieldDisplayed(), "Password Field is not displayed");
        log.info("Password Field appeared");

        loginPage.enterPassword(password);
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
