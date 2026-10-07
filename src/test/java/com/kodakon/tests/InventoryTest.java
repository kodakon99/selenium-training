package com.kodakon.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest {

    WebDriver driver;
    WebDriverWait wait;

    // Before each test: open Chrome, log in, wait until products are visible
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='inventory-item']")));
    }

    // TEST 1: the page shows exactly 6 products
    @Test
    public void inventoryShowsSixProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item']"));
        Assert.assertEquals(products.size(), 6, "Wrong number of products");
    }

    // TEST 2: the Backpack costs $29.99 (your locator #8)
    @Test
    public void backpackPriceIsCorrect() {
        String price = driver.findElement(By.xpath(
                "//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']"
        )).getText();
        Assert.assertEquals(price, "$29.99", "Backpack price is wrong");
    }

    // TEST 3: add the Onesie by its NAME (your locator #9)
    @Test
    public void addOnesieToCartByName() {
        By onesieButton = By.xpath(
                "//div[text()='Sauce Labs Onesie']/ancestor::div[@class='inventory_item']//button");

        driver.findElement(onesieButton).click();

        // The cart badge should now say 1
        String badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']"))).getText();
        Assert.assertEquals(badge, "1", "Cart badge count is wrong");

        // The button should have changed from "Add to cart" to "Remove"
        Assert.assertEquals(driver.findElement(onesieButton).getText(), "Remove",
                "Button text did not change after adding");
    }

    // TEST 4: sorting "Price (low to high)" really sorts the prices
    @Test
    public void sortByPriceLowToHigh() {
        // Choose the option in the dropdown
        Select sortDropdown = new Select(driver.findElement(
                By.cssSelector("[data-test='product-sort-container']")));
        sortDropdown.selectByValue("lohi");

        // Read all prices shown on screen, e.g. "$7.99" -> 7.99
        List<WebElement> priceElements = driver.findElements(
                By.cssSelector("[data-test='inventory-item-price']"));
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String text = priceElement.getText();                     // "$7.99"
            double value = Double.parseDouble(text.replace("$", "")); // 7.99
            actualPrices.add(value);
        }

        // Make a sorted copy, then compare: if the page sorted correctly, they match
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices,
                "Prices are not sorted low to high");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}