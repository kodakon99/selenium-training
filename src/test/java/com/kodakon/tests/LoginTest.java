package com.kodakon.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.openqa.selenium.WebElement;

public class LoginTest {

    // The "remote control" for the browser. Every test uses it.
    WebDriver driver;
    WebDriverWait wait;

    // Runs BEFORE each test: open a fresh browser on the login page
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();             // launches Chrome
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();      // full-screen window
        driver.get("https://www.saucedemo.com");  // go to the site
    }

    // TEST 1 (positive test): a valid user can log in
    @Test
    public void validUserCanLogIn() {
        // Find each field by its id and type into it
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Check the result: the page heading should say "Products"
        String heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        ).getText();
        Assert.assertEquals(heading, "Products", "Heading after login was wrong");

        // And the address should now be the inventory page
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Did not land on the inventory page");
    }

    // TEST 2 (negative test): a wrong password shows an error
    @Test
    public void wrongPasswordShowsError() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();

        String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
        ).getText();
        Assert.assertTrue(error.contains("do not match"),
                "Expected a 'do not match' error but got: " + error);
    }

    // Relative locator: the input BELOW the username box should be the password box
    @Test
    public void passwordFieldIsBelowUsername() {
        WebElement field = driver.findElement(
                with(By.tagName("input")).below(By.id("user-name")));
        Assert.assertEquals(field.getDomAttribute("id"), "password",
                "The field below username is not the password field");
    }

    // Runs AFTER each test, even if it failed: close the browser
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}