package basico2.tests;

import basico2.pages.FormAuthenticationPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class FormAuthenticationPageTest {
    private WebDriver driver;
    private FormAuthenticationPage formAuthPage;
    private WebDriverWait wait;
    private final String LOGIN_URL = "http://the-internet.herokuapp.com/login";
    private final String VALID_USER = "tomsmith";
    private final String VALID_PASS = "SuperSecretPassword!";
    private final Duration TIMEOUT = Duration.ofSeconds(10);

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, TIMEOUT);

        driver.get(LOGIN_URL);
        formAuthPage = new FormAuthenticationPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Teste 1: Login com Formulário Bem-Sucedido")
    void loginSuccessful() {
        formAuthPage.login(VALID_USER, VALID_PASS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        String expectedMessagePart = "You logged into a secure area";
        assertTrue(formAuthPage.getStatusMessageText().contains(expectedMessagePart),
                "O texto da mensagem de sucesso está incorreto.");
    }

    @Test
    @DisplayName("Teste 2: Login com Formulário Falhado (Credenciais Inválidas)")
    void testFailedLogin() {
        // O Heroku App usa 'tomsmith'/'SuperSecretPassword!'
        // Se a password estiver errada, a mensagem de erro é "Your password is invalid!" (Se o utilizador existir)
        formAuthPage.login(VALID_USER, "WrongPassword!");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        // Variável de debug removida (já sabemos o valor)
        String actualMessage = formAuthPage.getStatusMessageText();

        // CORREÇÃO FINAL DA ASSERÇÃO: Ajustamos o texto esperado para "password is invalid" (conforme a depuração)
        String expectedErrorMessage = "password is invalid";
        assertTrue(actualMessage.contains(expectedErrorMessage),
                "O texto da mensagem de erro está incorreto. Mensagem atual: [" + actualMessage + "]");
    }
}