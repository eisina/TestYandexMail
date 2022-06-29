package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends BasePage {

    @FindBy(className = "HeadBanner-Inner")
    private WebElement centralSection;

    @FindBy(xpath = "(//span[contains(text(),\"Log in\")]/ancestor::a)[1]")
    private WebElement loginButton;

    public StartPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage();
    }

    public boolean pageLoaded() {
        return centralSection.isDisplayed();
    }
}
