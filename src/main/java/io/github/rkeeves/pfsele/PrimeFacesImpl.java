package io.github.rkeeves.pfsele;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.github.rkeeves.pfsele.widget.Widget;
import io.github.rkeeves.pfsele.widget.WidgetFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static io.github.rkeeves.pfsele.condition.PrimefacesConditions.ajaxReady;

@Builder
@RequiredArgsConstructor
public class PrimeFacesImpl implements PrimeFaces {

    @Singular(value = "factory")
    private final Map<Class<?>, WidgetFactory<?>> factories;

    @Override
    @SuppressWarnings("unchecked")
    public <W extends Widget> WidgetFactory<W> get(Class<W> widgetClass) {
        return Optional.ofNullable(factories.get(widgetClass))
                .map(factory -> (WidgetFactory<W>) factory)
                .orElseThrow(() -> new Error("No factory was registered for type " + widgetClass));
    }

    @Override
    public SelenideElement $(By by) {
        return com.codeborne.selenide.Selenide.$(by).shouldBe(ajaxReady);
    }

    public PrimeFaces urlShouldMatch(final Predicate<String> urlPredicate) {
        final String url = expectToHaveUrlOrThrowError();
        boolean passed = urlPredicate.test(url);
        if (!passed) {
            final String message = String.format("Url did not match, actual url was '%s'", url);
            throw new Error(message);
        }
        return this;
    }

    private String expectToHaveUrlOrThrowError() {
        return Optional.of(WebDriverRunner.getWebDriver())
                .map(WebDriver::getCurrentUrl)
                .orElseThrow(() -> new Error("Current url was null"));
    }
}
