package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage extends BasePage {

    private By settingsButton = By.className("user-account_left-name");

    private By userName = By.xpath("//div/../a[contains(@class, 'legouser')]/span[contains(@class, 'user-account__name')]");

    private By logOutButton = By.xpath("//span[text()='Log out']");

    public MailPage() {
        super();
    }

    public void clickSettings() {
        driver.findElement(settingsButton).click();
    }

    public void logOut() {
        driver.findElement(logOutButton).click();
    }

    public boolean usernameDisplay() {
        webDriverWait.pollingEvery(Duration.ofSeconds(1)).until(ExpectedConditions.presenceOfElementLocated(userName));
        return driver.findElement(userName).isDisplayed();
    }
}

