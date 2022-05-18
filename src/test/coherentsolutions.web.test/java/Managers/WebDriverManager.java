package Managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    static WebDriver driver;

    public static WebDriver initDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return  driver;
    }
}