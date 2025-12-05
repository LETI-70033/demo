package org.example.form.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.form.FormPage;

@Epic("Vaadin demos")
@Feature("Form")
@Story("Fazer parte da comunidade")
public class FormTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000; // 8s
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    void devePermitirRegistoValidoNaComunidade() {
        FormPage page = new FormPage();

        page.openPage()
                .fillForm(
                        "Pedro",
                        "Aluno",
                        "pedro" + System.currentTimeMillis(),
                        "pedro.aluno@example.com",
                        "Senha123!"
                )
                .submit()
                .assertSuccess();
    }
}
