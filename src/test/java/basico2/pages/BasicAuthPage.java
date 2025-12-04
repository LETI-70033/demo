package basico2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// URL Base: https://the-internet.herokuapp.com/basic_auth
public class BasicAuthPage {

    // Localizador para a mensagem de sucesso (o parágrafo dentro da div .example)
    @FindBy(css = ".example p")
    public WebElement successMessage;

    // URL estática para usar diretamente no teste e by-passar o diálogo de autenticação
    public static final String SUCCESS_AUTH_URL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";

    public BasicAuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifica se a mensagem de sucesso está visível.
     * @return true se a mensagem estiver visível.
     */
    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    /**
     * Obtém o texto da mensagem de sucesso.
     * @return O texto da mensagem.
     */
    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}