package basico.tests;

import basico.pages.CheckboxesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CheckboxesPageTest {
    private WebDriver driver;
    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navega para a página específica das checkboxes
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Inicializa a página
        checkboxesPage = new CheckboxesPage(driver);
    }

    @AfterEach
    void tearDown() {
        // Fecha o browser no final
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testCheckboxes() throws InterruptedException{
        // 1. Verificar estado inicial
        // A Checkbox 1 costuma vir DESMARCADA
        assertFalse(checkboxesPage.checkbox1.isSelected(), "A Checkbox 1 devia estar desmarcada inicialmente");
        // A Checkbox 2 costuma vir MARCADA
        assertTrue(checkboxesPage.checkbox2.isSelected(), "A Checkbox 2 devia estar marcada inicialmente");

        Thread.sleep(2000);
        // 2. Interagir (Clicar na 1 para marcar)
        checkboxesPage.checkbox1.click();
        assertTrue(checkboxesPage.checkbox1.isSelected(), "A Checkbox 1 devia estar marcada após o clique");

        Thread.sleep(2000);
        // 3. Interagir (Clicar na 2 para desmarcar)
        checkboxesPage.checkbox2.click();
        assertFalse(checkboxesPage.checkbox2.isSelected(), "A Checkbox 2 devia estar desmarcada após o clique");

        Thread.sleep(2000);
    }
}