package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage extends BasePage {

    @FindBy(className = "user-account_left-name")
    private WebElement settingsButton;

    @FindBy(xpath = "//div/../a[contains(@class, 'legouser')]/span[contains(@class, 'user-account__name')]")
    private WebElement userName;

    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement logOutButton;

    public MailPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public MailPage clickSettings() {
        settingsButton.click();
        return this;
    }

    public MailPage logOut() {
        logOutButton.click();
        return this;
    }

    public boolean isUsernameDisplay() {
        webDriverWait.pollingEvery(Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(userName));
        return userName.isDisplayed();
    }
}
