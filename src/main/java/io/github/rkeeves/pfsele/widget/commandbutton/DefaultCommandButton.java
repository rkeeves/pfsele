package io.github.rkeeves.pfsele.widget.commandbutton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static io.github.rkeeves.pfsele.condition.PrimefacesConditions.ajaxReady;

@RequiredArgsConstructor
public class DefaultCommandButton implements CommandButton {

    @Getter
    private final By by;

    @Override
    public CommandButton click() {
        $(by).shouldBe(ajaxReady);
        $(by).scrollIntoView(false).click();
        return this;
    }
}
