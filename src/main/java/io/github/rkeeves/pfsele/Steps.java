package io.github.rkeeves.pfsele;

import com.codeborne.selenide.Selenide;
import io.github.rkeeves.pfsele.page.PrimeFacesDecorator;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public class Steps {

    private final PrimeFaces primeFaces;

    public static Steps open(String url) {
        Selenide.open(url);
        return new Steps(new PrimeFacesFactory().create());
    }

    public static Steps cont() {
        return new Steps(new PrimeFacesFactory().create());
    }

    public Steps then(Consumer<PrimeFaces> action) {
        action.accept(primeFaces);
        return this;
    }

    public <D extends PrimeFacesDecorator> Steps then(Function<PrimeFaces, D> decoratorFactory, Consumer<D> action) {
        action.accept(decoratorFactory.apply(primeFaces));
        return this;
    }
}
