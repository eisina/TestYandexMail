package TestCases;

import Locators.Locators;
import Managers.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Managers.WebDriverManager.quitDriver;
import static Utils.ConstantUtils.*;

public class SeleniumEasyTest {

    private WebDriver driver;
    private static Logger log = LogManager.getLogger();
    private WebDriverWait webDriverWait;

    @BeforeClass
    public void addSettings() {
        driver = WebDriverManager.initDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    @Description("Checking selecting multiselect option")
    @Feature("Multi Select Option")
    @TmsLink("123")
    public void multiselectTest() {
        driver.get(MULTISELECT_PAGE_URL);
        List<WebElement> multiSelectOptions = driver.findElements(Locators.MULTI_SELECT_OPTIONS);
        for (WebElement option : multiSelectOptions) {
            option.click();
            String optionText = option.getText();
            String expectedText = String.format("First selected option is : %s", optionText);
            driver.findElement(Locators.SELECT_BUTTON).click();
            String actualText = driver.findElement(Locators.SELECTED_TEXT).getText();
            Assert.assertEquals(actualText, expectedText, "Option is not selected");
            log.info(String.format("Option %s is selected", optionText));
        }
    }

    @Test
    @Description("Checking acceptingJava Script Confirm Box")
    @Feature("Confirm Box")
    @TmsLink("234")
    public void confirmBoxAcceptTest() {
        driver.get(CONFIRM_BOX_PAGE_URL);
        driver.findElement(Locators.CONFIRM_BOX_BUTTON).click();
        log.info("Button pressed");

        String expectedAlertMessage = "Press a button!";
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Wrong Alert message is displayed");
        log.info("Alert appeared");

        driver.switchTo().alert().accept();
        String expectedMessage = "You pressed OK!";
        String actualMessage = driver.findElement(Locators.CONFIRM_BOX_TEXT).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Wrong button is clicked");
        log.info("Alert accepted");
    }


    @Test
    @Description("Checking cancelling Java Script Confirm Box")
    @Feature("Confirm Box")
    @TmsLink("345")
    public void confirmBoxCancelTest() {
        driver.get(CONFIRM_BOX_PAGE_URL);
        driver.findElement(Locators.CONFIRM_BOX_BUTTON).click();
        log.info("Button pressed");

        String expectedAlertMessage = "Press a button!";
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Wrong Alert message is displayed");
        log.info("Alert appeared");

        driver.switchTo().alert().dismiss();
        String expectedMessage = "You pressed Cancel!";
        String actualMessage = driver.findElement(Locators.CONFIRM_BOX_TEXT).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Wrong button is clicked");
        log.info("Alert dismissed");
    }

    @Test
    @Description("Checking Java Script Alert Box")
    @Feature("Alert Box")
    @TmsLink("456")
    public void alertBoxTest() {
        driver.get(CONFIRM_BOX_PAGE_URL);
        driver.findElement(Locators.ALERT_BOX_BUTTON).click();
        log.info("Button pressed");

        String expectedAlertMessage = "Please enter your name";
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Wrong Alert message is displayed");
        log.info("Alert appeared");

        driver.switchTo().alert().sendKeys("Test Value");
        driver.switchTo().alert().accept();
        String actualMessage = driver.findElement(Locators.ALERT_BOX_TEXT).getText();
        String expectedMessage = "You have entered 'Test Value' !";
        Assert.assertEquals(actualMessage, expectedMessage, "Wrong button is clicked");
        log.info("Text entered and alert closed");
    }

    @Test
    @Description("Checking waiting fot the user to appear")
    @Feature("Wait Test")
    @TmsLink("567")
    public void waitForUserTest() {
        driver.get(DATA_LOADING_PAGE_URL);
        driver.findElement(Locators.GET_NEW_USER_BUTTON).click();
        webDriverWait.pollingEvery(Duration.ofMillis(800)).until(ExpectedConditions.elementToBeClickable(Locators.USER_IMAGE));
        Assert.assertTrue(driver.findElement(Locators.USER_IMAGE).isDisplayed(), "User is displayed");
        log.info("User appeared");
    }

    @Test
    @Description("Checking refreshing the page when download percentage >50")
    @Feature("Wait Test")
    @TmsLink("678")
    public void refreshDownloadTest() {
        driver.get(DOWNLOAD_PAGE_URL);
        driver.findElement(Locators.DOWNLOAD_BUTTON).click();
        while (Integer.parseInt(driver.findElement(Locators.PERCENT_CIRCLE).getText().replace("%", "")) < 50) {
            webDriverWait.withTimeout(Duration.ofSeconds(1));
        }
        driver.navigate().refresh();
        log.info("Page refreshed");

        Assert.assertEquals(driver.findElement(Locators.PERCENT_CIRCLE).getText(), "0%",
                "Wrong download percentage is displayed");
        log.info("Button is displayed");
    }

    @DataProvider(name = "Age and Salary")
    public Object[][] dpMethod() throws IOException {
        return new Object[][]{{60, 10000}};
    }

    @Test(dataProvider = "Age and Salary")
    @Description("Get records with definite Age and Salary")
    @Feature("Script")
    @TmsLink("890")
    public void returnEmployeeTest(int targetAge, int targetSalary) {

        driver.get(TABLE_PAGE_URL);
        Select elementNumber = new Select(driver.findElement(Locators.SHOW_ENTRIES_DROPDOWN));
        elementNumber.selectByValue("10");
        Assert.assertEquals(elementNumber.getFirstSelectedOption().getText(), "10", "Option 10 is not selected");
        log.info("Option '10' is selected in dropdown “Show () entries”");

        List<Employee> selectedEmployeesList = new ArrayList<>();
        boolean isNextPageButtonEnabled = true;
        do {
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
            int count = rows.size();
            IntStream.range(1, count)
                    .forEach(i -> {
                        String ageText = getCellValue(driver, "Age", i);
                        int age = Integer.valueOf(ageText);

                        String salaryText = getCellValue(driver, "Salary", i);
                        int salary = parsePrice(salaryText);

                        if (age > targetAge & salary > targetSalary) {
                            String name = getCellValue(driver, "Name", i);
                            String position = getCellValue(driver, "Position", i);
                            String office = getCellValue(driver, "Office", i);

                            Employee employee = new Employee(name, position, office);
                            selectedEmployeesList.add(employee);
                            log.info("TestCases.Employee match the criteria");
                        }
                    });
            if ((driver.findElements(Locators.NEXT_DISABLED_BUTTON)).size() < 1) {
                driver.findElement(Locators.NEXT_BUTTON).click();
                log.info("Button 'Next' is clicked");
            } else {
                isNextPageButtonEnabled = false;
                log.info("Last/Only page is displayed");
            }
        } while (isNextPageButtonEnabled);
        Stream<Employee> stream = selectedEmployeesList.stream();
        stream.forEach(e -> System.out.println(e.toString()));
        Assert.assertTrue(selectedEmployeesList.size() > 0, "No values match the criteria");
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public String getBrowserDetails() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        String os = cap.getPlatform().toString();
        String version = cap.getVersion().toString();
        return browserName + " " + os + " " + version;
    }

    @AfterMethod
    protected void addFailInfo(ITestResult result) throws IOException {
        if (!result.isSuccess()) {
            saveFailureScreenShot(driver);
            getBrowserDetails();
        }

    }

    @AfterClass
    public void quit() {
        quitDriver();
    }

    public static int parsePrice(String value) {
        String newValue = removeCommas(value);
        return Integer.parseInt(newValue.substring(1, newValue.length() - 2));
    }

    public static String removeCommas(String value) {
        return value.replaceAll(",", "");
    }

    public static String getCellValue(WebDriver driver, String columnName, int rowNumber) {
        return driver.findElement(By.xpath(String.format(Locators.CELL, columnName, rowNumber))).getText();
    }
}

