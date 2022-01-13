package io.github.rkeeves.pfsele.widget.inputtext;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static io.github.rkeeves.pfsele.condition.PrimefacesConditions.ajaxReady;

@RequiredArgsConstructor
public class DefaultInputText implements InputText {

    @Getter
    private final By by;

    @Override
    public InputText setValue(String newValue) {
        $(by).setValue(newValue).shouldBe(ajaxReady);
        return this;
    }

    @Override
    public InputText shouldHaveValue(String expectedValue) {
        $(by).shouldBe(ajaxReady).shouldHave(value(expectedValue));
        return this;
    }
}
