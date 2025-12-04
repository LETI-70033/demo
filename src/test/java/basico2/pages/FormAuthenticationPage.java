package basico2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormAuthenticationPage {

    private WebDriver driver;
    // Localizadores
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("#login button");
    // Localizador público para uso na classe de teste (WebDriverWait)
    public By statusMessageLocator = By.id("flash");

    public FormAuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    /**
     * Retorna o texto da mensagem de status limpo (sem '\n' e '×')
     */
    public String getStatusMessageText() {
        // Encontra o elemento e obtém o texto completo, que inclui o "\n×"
        String fullText = driver.findElement(statusMessageLocator).getText();

        // O replaceAll original pode falhar em diferentes sistemas.
        // Vamos usar uma abordagem mais simples: remover o símbolo 'x' e os espaços no final.
        return fullText.replace("×", "").trim();
    }

    public WebElement getStatusMessageElement() {
        return driver.findElement(statusMessageLocator);
    }
}