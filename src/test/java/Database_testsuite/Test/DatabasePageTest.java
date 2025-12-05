package Database_testsuite.Test;

import Database_testsuite.Pages.DatabasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabasePageTest {

    static {
        System.setProperty("allure.results.directory", "target/allure-results");
    }

    private DatabasePage databasePage;

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // Deixa o browser aberto se falhar para tu veres

        databasePage = new DatabasePage();
        databasePage.openPage();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void testMovieTableData() {
        Selenide.sleep(3000); // Espera 3 segundos para a tabela carregar visualmente

        // Vamos validar o "The Last Jedi"
        // Se este falhar, tenta mudar para um filme que tenhas a certeza que vês no ecrã
        databasePage.validateMovieRow("The Last Jedi", "2017", "Rian Johnson");

        System.out.println("Filme encontrado com sucesso!");
    }

    @Test
    void testSecondMovieImdbLink() {
        Selenide.sleep(2000); // Pausa para garantir que a tabela carregou

        // 1. Clicar no link do segundo filme
        databasePage.clickImdbLinkForSecondMovie();

        // 2. Trocar para o novo separador que abriu
        // (O browser abre o IMDB numa nova aba, o Selenide precisa de saber disso)
        Selenide.switchTo().window(1);

        Selenide.sleep(2000); // Pausa para veres o site do IMDB a abrir

        // 3. Validar que estamos no site do IMDB
        String currentUrl = webdriver().object().getCurrentUrl();
        assertTrue(currentUrl.contains("imdb.com"), "O URL devia conter 'imdb.com'");

        System.out.println("Sucesso! O link abriu: " + currentUrl);

        // Opcional: Fechar a aba do IMDB e voltar à principal
        Selenide.closeWindow();
        Selenide.switchTo().window(0);
    }
}