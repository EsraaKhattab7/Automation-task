package com.tests;

import com.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

     public WebDriver driver;
     public Properties pro;

     public WebDriver initializeDriver() {
         try {
             pro = new Properties();
             FileInputStream fis = new FileInputStream("src/test/java/resources/test-data/Global.properties");
             pro.load(fis);
             String browser = pro.getProperty("BrowserName");
             if (browser.contains("chrome"))
             {
                 driver = new ChromeDriver();
             }
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
             driver.manage().window().maximize();

             return driver;
         } catch (IOException e) {

             e.printStackTrace();
             return null;
         }
     }

    public HomePage homePage;
    @BeforeMethod(alwaysRun=true)
    public HomePage launchApplication()
    {
        driver = initializeDriver();
        if (driver != null) {
            homePage = new HomePage(driver);
            driver.get(pro.getProperty("URL"));
            return homePage;
        }
        return null;
    }
}
