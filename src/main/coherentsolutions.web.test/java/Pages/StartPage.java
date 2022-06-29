package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends BasePage {

    @FindBy(className = "HeadBanner-Inner")
    private WebElement centralSection;

    @FindBy(xpath = "(//span[contains(text(),\"Log in\")]/ancestor::a)[1]")
    private WebElement loginButton;

    public StartPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver, webDriverWait);
    }

    public boolean isPageLoaded() {
        return centralSection.isDisplayed();
    }
}
