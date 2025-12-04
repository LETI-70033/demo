package basico2.tests;

import basico2.pages.BasicAuthPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthPageTest {
    private WebDriver driver;
    private BasicAuthPage basicAuthPage;
    private WebDriverWait wait;

    // Credenciais válidas: admin:admin

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Teste de Autenticação Básica Bem-Sucedida - Mensagem Visível")
    void isSuccessMessageDisplayed() throws InterruptedException {

        Thread.sleep(2000);
        // Ação: Navegar diretamente para a URL com credenciais embutidas
        driver.get(BasicAuthPage.SUCCESS_AUTH_URL);
        Thread.sleep(2000);
        // Inicializa a Page Object após a navegação
        basicAuthPage = new BasicAuthPage(driver);
        Thread.sleep(2000);
        // Asserção: A mensagem de sucesso deve estar visível
        // É importante notar que o elemento só existe se a autenticação for bem-sucedida.
        assertTrue(basicAuthPage.isSuccessMessageDisplayed(), "A mensagem de sucesso após Basic Auth deve estar visível.");
    }

    @Test
    @DisplayName("Teste de Autenticação Básica Bem-Sucedida - Conteúdo da Mensagem")
    void getSuccessMessageText() throws InterruptedException {
        Thread.sleep(2000);
        // Ação: Navegar diretamente para a URL com credenciais embutidas
        driver.get(BasicAuthPage.SUCCESS_AUTH_URL);
        Thread.sleep(2000);
        // Inicializa a Page Object após a navegação
        basicAuthPage = new BasicAuthPage(driver);
        Thread.sleep(2000);
        // Asserção: Validar o texto da mensagem
        String expectedMessage = "Congratulations! You must have the proper credentials.";
        assertEquals(expectedMessage, basicAuthPage.getSuccessMessageText(),
                "O texto da mensagem de sucesso está incorreto ou incompleto.");
    }
}