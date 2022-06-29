package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MailPage extends BasePage {

    @FindBy(className = "user-account_left-name")
    private WebElement settingsButton;

    @FindBy(xpath = "//div/../a[contains(@class, 'legouser')]/span[contains(@class, 'user-account__name')]")
    private WebElement userName;

    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement logOutButton;

    public MailPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void clickSettings() {
        settingsButton.click();
    }

    public void logOut() {
        logOutButton.click();
    }

    public boolean usernameDisplay() {
        webDriverWait.pollingEvery(Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(userName));
        return userName.isDisplayed();
    }
}
