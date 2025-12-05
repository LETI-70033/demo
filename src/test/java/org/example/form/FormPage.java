package org.example.form;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class FormPage {

    public static final String URL = "https://vaadin-form-example.demo.vaadin.com/";

    // Campos do formulário
    private final SelenideElement firstName     = $("[aria-label='First name']");
    private final SelenideElement lastName      = $("[aria-label='Last name']");
    private final SelenideElement userHandle    = $("[aria-label='User handle']");
    private final SelenideElement email         = $("[type='email']");
    private final SelenideElement password      = $("[aria-label='Wanted password']");
    private final SelenideElement passwordAgain = $("[aria-label='Password again']");

    // checkbox de marketing (pode ter de ser ajustado)
    private final SelenideElement marketing =
            $x("//vaadin-checkbox[contains(.,'Allow Marketing')]");

    // botão de submit – vê o texto real do botão e ajusta se for preciso
    private final SelenideElement joinButton =
            $x("//button[contains(.,'Join the community') or contains(.,'Join')]");

    // mensagem de sucesso – ajusta para o texto que aparecer no topo/após submit
    private final SelenideElement successMessage =
            $x("//*[contains(text(),'Thank you') or contains(text(),'Welcome')]");

    @Step("Abrir página do formulário")
    public FormPage openPage() {
        open(URL);
        return this;
    }

    @Step("Preencher dados do utilizador")
    public FormPage fillForm(String f, String l, String handle, String mail, String pwd) {
        firstName.shouldBe(visible).setValue(f);
        lastName.shouldBe(visible).setValue(l);
        userHandle.shouldBe(visible).setValue(handle);
        email.shouldBe(visible).setValue(mail);
        password.shouldBe(visible).setValue(pwd);
        passwordAgain.shouldBe(visible).setValue(pwd);
        marketing.click();
        return this;
    }

    @Step("Submeter formulário")
    public FormPage submit() {
        joinButton.shouldBe(visible).click();
        return this;
    }

    @Step("Verificar que aparece mensagem de sucesso")
    public void assertSuccess() {
        successMessage.shouldBe(visible);
    }
}
