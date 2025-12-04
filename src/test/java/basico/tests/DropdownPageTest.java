package basico.tests;

import basico.pages.DropdownPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DropdownPageTest {
    private WebDriver driver;
    private DropdownPage dropdownPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navegar para a página do dropdown
        driver.get("https://the-internet.herokuapp.com/dropdown");

        dropdownPage = new DropdownPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testDropdownOptions() throws InterruptedException {
        // Pausa inicial para veres a página a abrir
        Thread.sleep(2000);

        // 1. Selecionar "Option 1"
        dropdownPage.selectOption("Option 1");

        // Verificar se ficou selecionado
        assertEquals("Option 1", dropdownPage.getSelectedOption(), "A opção selecionada devia ser 'Option 1'");

        // Pausa de 2 segundos
        Thread.sleep(2000);

        // 2. Selecionar "Option 2"
        dropdownPage.selectOption("Option 2");

        // Verificar se mudou
        assertEquals("Option 2", dropdownPage.getSelectedOption(), "A opção selecionada devia ser 'Option 2'");

        // Pausa final antes de fechar
        Thread.sleep(2000);
    }
}