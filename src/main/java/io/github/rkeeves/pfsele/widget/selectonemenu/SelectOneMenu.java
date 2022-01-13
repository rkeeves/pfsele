package io.github.rkeeves.pfsele.widget.selectonemenu;

import io.github.rkeeves.pfsele.widget.Widget;

public interface SelectOneMenu extends Widget {

    SelectOneMenu setValue(String newValue);

    SelectOneMenu shouldHaveValue(String expectedValue);
}
