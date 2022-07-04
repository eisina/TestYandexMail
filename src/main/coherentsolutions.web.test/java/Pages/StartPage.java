package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends BasePage {

    private By centralSection = By.className("HeadBanner-Inner");

    private By loginButton = By.xpath("(//span[contains(text(),\"Log in\")]/ancestor::a)[1]");

    public StartPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver, webDriverWait);
    }

    public boolean isPageLoaded() {
        return driver.findElement(centralSection).isDisplayed();
    }
}
