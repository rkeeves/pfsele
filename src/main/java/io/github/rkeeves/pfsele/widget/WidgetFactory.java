package io.github.rkeeves.pfsele.widget;

import org.openqa.selenium.By;

@FunctionalInterface
public interface WidgetFactory<W> {

    W by(By by);
}
