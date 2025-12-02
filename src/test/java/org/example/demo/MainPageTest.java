package org.example.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.JavascriptExecutor;
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");

        // **** GESTÃO DE COOKIES ****
        try {
            By cookieButtonLocator = By.xpath("//button[text()='Accept All']");
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieButtonLocator));
            cookieButton.click();
            System.out.println("Cookies aceites com sucesso.");
        } catch (Exception e) {
            System.out.println("Banner de cookies não intercetado ou já fechado.");
        }
        // ****************************

        mainPage = new MainPage(driver);

        // Espera pelo botão de pesquisa após carregamento/cookies. (Localizador: data-test='site-header-mobile-search-action')
        wait.until(ExpectedConditions.visibilityOf(mainPage.searchButton));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    /*@Test
    public void search() {
        // 1. Clica no botão de pesquisa (lupa)
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        // 2. CORREÇÃO: Campo de texto usa 'data-test-id'
        By searchFieldLocator = By.cssSelector("[data-test-id='search-input']");
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));

        // 3. Digita o texto
        searchField.sendKeys("Selenium");

        // 4. Clica no botão de submissão
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        // 5. Valida o resultado
        WebElement searchPageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-test-id='search-input']")));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }*/


   /* @Test
    public void toolsMenu()throws TimeoutException {
        // 1. Clica no item de menu "Developer Tools"
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        // 2. CORREÇÃO CRÍTICA: O pop-up (submenu) tem o seletor móvel 'mobile-main-submenu'
        By menuPopupLocator = By.cssSelector("[data-test='site-header-overlay']");
        WebElement menuPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(menuPopupLocator));

        // 3. Asserção
        assertTrue(menuPopup.isDisplayed());
    }*/

    @Test
    public void navigationToAllTools() {
        // O teste original é corrigido para o fluxo real:
        // 1. Abrir o menu principal (toolsMenu)
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        // Esperar que o pop-up esteja visível (usando o localizador que funcionou no teste anterior)
        By menuPopupLocator = By.cssSelector("[data-test='main-submenu-suggestions']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuPopupLocator));

        // 2. Clicar no botão "Find Your Tools" (findYourToolsButton) para navegar.
        // CORREÇÃO ESSENCIAL: Usamos JavascriptExecutor para forçar o clique e evitar o ElementClickInterceptedException.
        WebElement findButton = wait.until(ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", findButton);

        // 3. Esperar que o título principal H1 da nova página apareça
        // CORREÇÃO ESSENCIAL: O localizador é atualizado para o h1.wt-hero-title.
        By productsPageLocator = By.xpath("//h1[contains(text(), 'Find the right tool')]");
        WebElement productsList = wait.until(ExpectedConditions.visibilityOfElementLocated(productsPageLocator));

        // 4. Asserção
        assertTrue(productsList.isDisplayed());
        // Validamos o texto do título H1:
        assertEquals("Find the right tool", productsList.getText().trim());
    }
}