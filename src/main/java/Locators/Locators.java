package Locators;

import org.openqa.selenium.By;

public class Locators {

    public static final By START_PAGE = By.className("HeadBanner-Inner");
    public static final By LOGIN_PAGE = By.className("passp-auth-content");
    public static final By LOGIN_FIELD = By.xpath("//input[@name='login']");
    public static final By LOGIN_BUTTON = By.xpath("(//span[contains(text(),\"Log in\")]/ancestor::a)[1]");
    public static final By LOGOUT_BUTTON = By.xpath("//span[text()='Log out']");
    public static final By USER_NAME = By.xpath("//div/../a[contains(@class, 'legouser')]/span[contains(@class, 'user-account__name')]");
    public static final By SETTING_BUTTON = By.className("user-account_left-name");
    public static final By PROCEED_BUTTON = By.id("passp:sign-in");
    public static final By PASSWORD_FIELD = By.id("passp-field-passwd");
    public static final By MESSAGE_BLOCK = By.className("mail-Page-Body");
    public static final By MULTI_SELECT_OPTIONS = By.xpath("//select[@multiple]/option");
    public static final By SELECT_BUTTON = By.xpath("//button[@id='printMe']");
    public static final By SELECTED_TEXT = By.xpath("//p[@class='getall-selected']");
    public static final By CONFIRM_BOX_BUTTON = By.xpath("//b[contains(text(),'confirm box:')]/following-sibling::button");
    public static final By CONFIRM_BOX_TEXT = By.xpath("//p[@id='confirm-demo']");
    public static final By ALERT_BOX_BUTTON = By.xpath("//b[contains(text(),'prompt box')]/following-sibling::button");
    public static final By ALERT_BOX_TEXT = By.xpath("//p[@id='prompt-demo']");
    public static final By USER_IMAGE = By.xpath("//div/img");
    public static final By GET_NEW_USER_BUTTON = By.xpath("//button[@id='save']");
    public static final By DOWNLOAD_BUTTON = By.xpath("//button[@id='cricle-btn']");
    public static final By PERCENT_CIRCLE = By.className("percenttext");
    public static final By SHOW_ENTRIES_DROPDOWN = By.xpath("//*[@name='example_length']");
    public static final By NEXT_BUTTON = By.id("example_next");
    public static final By NEXT_DISABLED_BUTTON = By.xpath("//a[contains(@class, 'next disabled')]");
    public static final String CELL = "(//td[count(//th[text()='%s']/preceding-sibling::th)+1])[%d]";
}
