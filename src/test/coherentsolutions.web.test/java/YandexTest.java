import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class YandexTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String PASSWORD = "test123test321";
    private final String LOGIN = "katetestng";

    @FindBy(xpath = "(//span[contains(text(),\"Log in\")]/ancestor::a)[1]")
    WebElement loginButton;

    @FindBy(xpath = "//input[@name='login']")
    WebElement idField;

    @FindBy(xpath = "(//span[contains(text(),\"Log in\")]/ancestor::button)[1]")
    WebElement proceedButton;

    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    WebElement passwordField;

    @FindBy(xpath = "//div[@data-key=\"view=messages-list\"]")
    WebElement personalInfoBlock;


    @BeforeMethod
    public void addSettings() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://mail.yandex.com/");
        loginButton.click();
        idField.sendKeys(LOGIN);
        proceedButton.click();
        Thread.sleep(1000);
        passwordField.sendKeys(PASSWORD);
        proceedButton.click();
        Thread.sleep(5000);
        Assert.assertTrue(personalInfoBlock.isDisplayed());
    }
}
