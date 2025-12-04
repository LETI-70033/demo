package basico.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

// page_url = https://the-internet.herokuapp.com/dropdown
public class DropdownPage {

    // O ID do elemento select na página é 'dropdown'
    @FindBy(id = "dropdown")
    public WebElement dropdownElement;

    public DropdownPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Metodo auxiliar para selecionar uma opção pelo texto visível (ex: "Option 1")
    public void selectOption(String text) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }

    // Metodo auxiliar para obter o texto da opção que está selecionada no momento
    public String getSelectedOption() {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }
}