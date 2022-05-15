import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class Locators {

    WebDriver driver = new ChromeDriver();

    //ID Locator, Log In button
    @FindBy(id = "passp:sign-in")
    WebElement loginByID;

    //Name Locator, Log In field
    @FindBy(id = "login")
    WebElement logInByName;

    //ClassName Locator,Log in field
    @FindBy(className = "Textinput-Control")
    WebElement logInByClassName;

    //TagName Locator, button
    @FindBy(tagName = "button")
    WebElement logInByTagName;

    //LinkText, Forgot password link
    @FindBy(linkText = "Forgot your password?")
    WebElement logInByLinkText;

    //PartialText, Forgot password link
    @FindBy(partialLinkText = "Forgot")
    WebElement logInByPartialLinkText;

    //CSS, Log In button
    @FindBy(css = "Button2")
    WebElement logInByCSS;
}