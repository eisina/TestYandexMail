package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;
import java.time.Duration;

public class LoginPage extends BasePage {

    private By loginField = By.xpath("//input[@name='login']");

    private By passwordField = By.id("passp-field-passwd");

    private By proceedButton = By.id("passp:sign-in");

    public LoginPage() {
        super();
    }

    public MailPage enterLogin(String login) {
        driver.findElement(loginField).sendKeys(login);
        return new MailPage();
    }

    public MailPage clickLogin() {
        driver.findElement(proceedButton).click();
        return new MailPage();
    }

    public boolean passwordFieldDisplayed() {
        webDriverWait.pollingEvery(Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(passwordField));
        return driver.findElement(passwordField).isDisplayed();
    }

    public boolean loginFieldDisplayed() {
        return driver.findElement(loginField).isDisplayed();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

}
