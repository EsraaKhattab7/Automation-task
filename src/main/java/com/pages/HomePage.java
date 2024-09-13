package com.pages;
import com.Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage {

    private By womenFragrancesLink = By.linkText("Women Fragrances"); // Women Fragrances
    private  By   pageHeader  = By.cssSelector("div > h1");
    double totalPrice=0;
    private By allProducts = By.cssSelector("div.purchase-section");
    private By priceElementName= By.tagName("h6");
    private By addToCartBtn = By.tagName("button");
    private By totalCheckoutPrices  =By.xpath("//strong[@translate='no']");

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;

    }


     private By getTitle(String text) {
        return By.xpath("//span[text()='" + text + "']");  // Fragrances
    }


    public void select_WomenFragrances_From_Fragrances_Tab_In_The_Upper_Bar(String maintitle)
    {
          Utility.hoverForElementText(driver , getTitle(maintitle) , womenFragrancesLink);
     }

    public String verifyPageHeader()
    {
        return driver.findElement(pageHeader).getText();
    }

    private By checkoutBtn = By.xpath("//input[@name='checkout']");
    public void clickOnCheckout(){
        Utility.scrollToElement(driver, checkoutBtn);
        driver.findElement(checkoutBtn).click();
    }



    public double addPricesToCartWithLessThan(double targetPrice) {
        Utility.scrollToElement(driver, allProducts);
        List<WebElement> listProducts = driver.findElements(allProducts);

        for (WebElement product : listProducts) {
            WebElement priceElement = product.findElement(priceElementName);
            String priceWithoutCurrency = priceElement.getText().replace("EGP", "").trim();
            String priceWithoutCommas = priceWithoutCurrency.replace(",", "");
            int decimalIndex = priceWithoutCommas.indexOf('.');
            if (decimalIndex != -1) {
                priceWithoutCommas = priceWithoutCommas.substring(0, decimalIndex);
            }
            System.out.println("priceWithoutCommas: " + priceWithoutCommas);
            double price = Double.parseDouble(priceWithoutCommas);
            while (price < targetPrice)
            {
                WebElement addToCartButton = product.findElement(addToCartBtn);
                addToCartButton.click();
                totalPrice = totalPrice + price;
            }
        }
        return totalPrice;
    }


    public double verifyTotalPricesAtCheckout()
    {
        String expectationalPrice= driver.findElement(totalCheckoutPrices).getText();
        String priceWithoutCurrency = expectationalPrice.replace("E£", "").trim();
        String priceWithoutCommas = priceWithoutCurrency.replace(",", "");
        int decimalIndex = priceWithoutCommas.indexOf('.');
        if (decimalIndex != -1) {
            priceWithoutCommas = priceWithoutCommas.substring(0, decimalIndex);
        }
        double expected_price = Double.parseDouble(priceWithoutCommas);
        return expected_price;
    }

}
