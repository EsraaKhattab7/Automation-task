package com.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


public class Utility {

    private static WebDriver driver;

    public Utility(WebDriver driver)
    {
        this.driver = driver;
    }



    //TODO:  Scroll to specific element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", findWebElement(driver, locator));
    }

    //Convert Locator to Web Element
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void hoverForElementText(WebDriver driver,By mainLocator , By subLocator)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(mainLocator)).build().perform();
        actions.moveToElement(driver.findElement(subLocator)).click().build().perform();
    }
}
