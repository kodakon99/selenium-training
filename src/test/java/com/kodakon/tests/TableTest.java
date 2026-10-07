package com.kodakon.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TableTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        // Turn the file path into a browser address like file:///Users/.../tables.html
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    // #13: the table body has 4 rows
    @Test
    public void tableHasFourRows() {
        int rows = driver.findElements(By.cssSelector("#table1 tbody tr")).size();
        Assert.assertEquals(rows, 4, "Wrong number of rows");
    }

    // #14: find Conway's email by starting from his last name
    @Test
    public void conwayEmailIsCorrect() {
        String email = driver.findElement(By.xpath(
                "//table[@id='table1']//td[text()='Conway']/following-sibling::td[2]"
        )).getText();
        Assert.assertEquals(email, "tconway@earthlink.net", "Conway's email is wrong");
    }

    // #15: Bach's row has a visible delete link
    @Test
    public void bachHasDeleteLink() {
        boolean visible = driver.findElement(By.xpath(
                "//table[@id='table1']//td[text()='Bach']/ancestor::tr//a[@href='#delete']"
        )).isDisplayed();
        Assert.assertTrue(visible, "Delete link for Bach is not visible");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}