package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void verify(DataHelper.VerificationCode code) {
        codeField.setValue(code.getCode());
        verifyButton.click();
    }

    public DashboardPage goToDashboardPage() {
        return new DashboardPage();
    }

    public void getErrorIfInvalidVerify() {
        $(byText("Ошибка")).shouldBe(visible);
        $(byText("Неверно указан код! Попробуйте ещё раз.")).shouldBe(visible);
    }
}