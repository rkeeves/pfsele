package io.github.rkeeves.pfsele.widget.inputtext;

import io.github.rkeeves.pfsele.widget.Widget;

public interface InputText extends Widget {

    InputText setValue(String newValue);

    InputText shouldHaveValue(String expectedValue);
}
