package com.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartScenario extends  BaseTest {

    @Test
    public void addToCartEndToEnd() {

        homePage.select_WomenFragrances_From_Fragrances_Tab_In_The_Upper_Bar("Fragrances");
        String actualText = homePage.verifyPageHeader();
        Assert.assertTrue(actualText.contains("Women Fragrances"));

        double totalPrice=  homePage.addPricesToCartWithLessThan(2500);

        System.out.println("totalPrice:" + totalPrice);

        homePage.clickOnCheckout();

        double expected_price =homePage.verifyTotalPricesAtCheckout();

        Assert.assertEquals(totalPrice,expected_price);

    }
}
