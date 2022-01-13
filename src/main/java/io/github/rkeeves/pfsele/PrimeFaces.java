package io.github.rkeeves.pfsele;

import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.pfsele.widget.Widget;
import io.github.rkeeves.pfsele.widget.WidgetFactory;
import org.openqa.selenium.By;

import java.util.function.Predicate;

public interface PrimeFaces {

    <W extends Widget> WidgetFactory<W> get(Class<W> widgetClass);

    SelenideElement $(By by);

    default SelenideElement $(final String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    default SelenideElement $x(final String xpath) {
        return $(By.xpath(xpath));
    }

    PrimeFaces urlShouldMatch(final Predicate<String> urlPredicate);

    default PrimeFaces urlShouldHaveSuffix(String expectedUrlSuffix) {
        return urlShouldMatch(url -> url.endsWith(expectedUrlSuffix));
    }

    default PrimeFaces urlShouldBeExactly(String expectedExactUrl) {
        return urlShouldMatch(url -> url.equals(expectedExactUrl));
    }
}
