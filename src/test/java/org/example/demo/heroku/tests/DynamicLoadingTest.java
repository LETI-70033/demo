package org.example.demo.heroku.tests;

import org.example.demo.heroku.pages.DynamicLoadingPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DynamicLoadingTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private DynamicLoadingPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        page = new DynamicLoadingPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testHelloWorldAppears() {
        page.clickStart();

        WebElement finish = wait.until(
                ExpectedConditions.visibilityOf(page.getFinishText())
        );

        Assertions.assertEquals("Hello World!", finish.getText());
    }
}
