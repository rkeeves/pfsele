package io.github.rkeeves.pfsele.page;

import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.pfsele.PrimeFaces;
import io.github.rkeeves.pfsele.widget.Widget;
import io.github.rkeeves.pfsele.widget.WidgetFactory;
import org.openqa.selenium.By;

import java.util.function.Predicate;

public interface PrimeFacesDecorator extends PrimeFaces {

    PrimeFaces getPrimeFaces();

    default <W extends Widget> WidgetFactory<W> get(Class<W> widgetClass) {
        return getPrimeFaces().get(widgetClass);
    }

    default SelenideElement $(By by) {
        return getPrimeFaces().$(by);
    }

    default PrimeFaces urlShouldMatch(final Predicate<String> urlPredicate) {
        return getPrimeFaces().urlShouldMatch(urlPredicate);
    }
}
