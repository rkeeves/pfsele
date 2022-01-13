package io.github.rkeeves.pfsele.widget.selectonemenu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.github.rkeeves.pfsele.condition.PrimefacesConditions.ajaxReady;

@RequiredArgsConstructor
public class DefaultSelectOneMenu implements SelectOneMenu {

    @Getter
    private final By by;

    @Override
    public SelectOneMenu setValue(final String newValue) {
        $(by).shouldBe(ajaxReady);
        final String id = $(by).attr("id");
        final String script = String.format("PrimeFaces.getWidgetById('%s').selectValue('%s');", id, newValue);
        executeJavaScript(script);
        $(by).shouldBe(ajaxReady);
        shouldHaveValue(newValue);
        return this;
    }

    @Override
    public SelectOneMenu shouldHaveValue(String expectedValue) {
        $(by).shouldBe(ajaxReady);
        final String id = $(by).attr("id");
        final By label = By.id(id + "_label");
        $(label).shouldHave(text(expectedValue));
        return this;
    }
}
