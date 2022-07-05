package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;

    @FindBy(id = "passp:sign-in")
    private WebElement proceedButton;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordFideld;

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public MailPage enterLogin(String login) {
        loginField.sendKeys(login);
        return new MailPage(driver, webDriverWait);
    }

    public MailPage clickLogin() {
        proceedButton.click();
        return new MailPage(driver, webDriverWait);
    }

    public boolean isPasswordFieldDisplayed() {
        webDriverWait.pollingEvery(Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(passwordFideld));
        return passwordFideld.isDisplayed();
    }

    public boolean isLoginFieldDisplayed() {
        return loginField.isDisplayed();
    }

    public LoginPage enterPassword(String password) {
        passwordFideld.sendKeys(password);
        return this;
    }

}
