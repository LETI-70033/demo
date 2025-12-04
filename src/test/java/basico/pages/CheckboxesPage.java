package basico.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://the-internet.herokuapp.com/checkboxes
public class CheckboxesPage {

    // Localiza a primeira checkbox (geralmente desmarcada por defeito)
    @FindBy(css = "#checkboxes input:nth-of-type(1)")
    public WebElement checkbox1;

    // Localiza a segunda checkbox (geralmente marcada por defeito)
    @FindBy(css = "#checkboxes input:nth-of-type(2)")
    public WebElement checkbox2;

    public CheckboxesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Opcional: Métodos auxiliares se quiseres encapsular a lógica
    public boolean isCheckbox1Selected() {
        return checkbox1.isSelected();
    }

    public void toggleCheckbox1() {
        checkbox1.click();
    }
}