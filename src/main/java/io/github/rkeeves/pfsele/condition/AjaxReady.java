package io.github.rkeeves.pfsele.condition;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

public class AjaxReady extends Condition {

    public AjaxReady() {
        super("ajax ready");
    }

    @Nonnull
    @CheckReturnValue
    @Override
    public CheckResult check(final Driver driver, final WebElement element) {
        final String script = "return document.readyState === 'complete'"
                + " && (!window.jQuery || jQuery.active == 0)"
                + " && (!window.PrimeFaces || (PrimeFaces.ajax.Queue.isEmpty() && (!PrimeFaces.animationActive || PrimeFaces.animationActive === false)))"
                + " && (!window.pfselenium || (pfselenium.xhr == null && pfselenium.navigating === false));";
        final Boolean isReady = driver.executeJavaScript(script);
        return new CheckResult(isReady, isReady ? "ready" : "ajax running");
    }
}
