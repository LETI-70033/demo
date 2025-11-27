package org.example.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            // 1. Tenta encontrar o botão de aceitar
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button[data-test='allow-all-cookies-button'], .ch2-allow-all-btn")
            ));

            // 2. Clica via JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieButton);

            // 3. NOVO PASSO: Espera que o banner (o contentor que bloqueia) desapareça
            // O erro dizia que o elemento de bloqueio era a classe 'ch2-container'
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ch2-container")));

            System.out.println("Banner de cookies fechado e desaparecido.");

        } catch (TimeoutException e) {
            System.out.println("O Banner não apareceu ou já desapareceu.");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchField = driver.findElement(By.cssSelector("[data-test-id='search-input']"));

        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test-id='search-input']"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("[data-test='main-menu']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> {
            return menuPopup.isDisplayed();
        });
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
