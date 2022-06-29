package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;

    @FindBy(id = "passp:sign-in")
    private WebElement proceedButton;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordFideld;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public MailPage enterLogin(String login) {
        loginField.sendKeys(login);
        return new MailPage();
    }

    public MailPage clickLogin() {
        proceedButton.click();
        return new MailPage();
    }

    public boolean passwordFieldDisplayed() {
        webDriverWait.pollingEvery(Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(passwordFideld));
        return passwordFideld.isDisplayed();
    }

    public boolean loginFieldDisplayed() {
        return loginField.isDisplayed();
    }

    public void enterPassword(String password) {
        passwordFideld.sendKeys(password);
    }

}
