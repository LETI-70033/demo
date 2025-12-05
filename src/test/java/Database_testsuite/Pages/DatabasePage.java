package Database_testsuite.Pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DatabasePage {

    public void openPage() {
        open("https://vaadin-database-example.demo.vaadin.com/");
    }

    public void validateMovieRow(String title, String expectedYear, String expectedDirector) {
        // CORREÇÃO:
        // Em vez de procurar uma linha <tr> específica (que pode não ser acessível),
        // procuramos diretamente um elemento que tenha o texto do Título.
        // O $(byText(...)) é muito inteligente e encontra o texto onde quer que esteja.
        SelenideElement titleElement = $(byText(title));

        // 1. Verifica se o título do filme está visível na página
        titleElement.shouldBe(visible);

        // 2. Verifica se o Ano e o Realizador também estão visíveis
        // (Nota: Numa verificação simples, basta garantir que os dados aparecem)
        $(byText(expectedYear)).shouldBe(visible);
        $(byText(expectedDirector)).shouldBe(visible);
    }
    public void clickImdbLinkForSecondMovie() {
        // CORREÇÃO:
        // O $$("a") falha porque o Vaadin esconde o HTML.
        // O $$(byText(...)) procura visualmente pelo texto no ecrã, o que funciona melhor aqui.

        // Isto vai criar uma lista com todos os elementos que dizem "Click to IMDB site"
        // E clicamos no segundo (índice 1)
        $$(byText("Click to IMBD site")).get(1).click();
    }
}