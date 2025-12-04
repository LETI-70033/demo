package org.example.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Esperas explícitas comuns aos testes
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.jetbrains.com/");

        // Tentativa de fechar o aviso de cookies (se existir)
        try {
            WebElement cookiesBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[text()='Accept All']")
                    )
            );
            cookiesBtn.click();
            System.out.println("Janela de cookies fechada.");
        } catch (Exception ignored) {
            System.out.println("Nenhum aviso de cookies presente.");
        }

        mainPage = new MainPage(driver);

        // Só avança quando o botão de pesquisa estiver realmente visível
        wait.until(ExpectedConditions.visibilityOf(mainPage.searchButton));
    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    // ------------------------------------------------------------
    // TESTE 1 – Pesquisa através da barra de pesquisa
    // ------------------------------------------------------------
    @Test
    public void search() {
        // Abre o campo da pesquisa
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        // Aponta para o input correto
        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test-id='search-input']")
                )
        );

        searchInput.sendKeys("Selenium");

        // Botão de submissão da pesquisa
        WebElement submit = driver.findElement(
                By.cssSelector("button[data-test='full-search-button']")
        );
        submit.click();

        // Verificação final
        WebElement resultField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[data-test-id='search-input']")
        ));

        assertEquals("Selenium", resultField.getAttribute("value"));
    }

    // ------------------------------------------------------------
    // TESTE 2 – Abrir o menu de ferramentas
    // ------------------------------------------------------------
    @Test
    public void toolsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        WebElement menu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='site-header-overlay']")
                )
        );

        assertTrue(menu.isDisplayed());
    }

    // ------------------------------------------------------------
    // TESTE 3 – Navegação para a página “All Tools”
    // ------------------------------------------------------------
    @Test
    public void navigationToAllTools() {

        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='main-submenu-suggestions']")
        ));

        // Evita problemas de overlay utilizando JS
        WebElement goToTools = wait.until(
                ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton)
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", goToTools);

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Find the right tool')]")
        ));

        assertTrue(pageTitle.isDisplayed());
        assertEquals("Find the right tool", pageTitle.getText().trim());
    }
}
