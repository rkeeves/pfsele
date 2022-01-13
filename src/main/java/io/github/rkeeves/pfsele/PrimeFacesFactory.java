package io.github.rkeeves.pfsele;

import io.github.rkeeves.pfsele.widget.commandbutton.CommandButton;
import io.github.rkeeves.pfsele.widget.commandbutton.DefaultCommandButton;
import io.github.rkeeves.pfsele.widget.inputtext.DefaultInputText;
import io.github.rkeeves.pfsele.widget.inputtext.InputText;
import io.github.rkeeves.pfsele.widget.selectonemenu.DefaultSelectOneMenu;
import io.github.rkeeves.pfsele.widget.selectonemenu.SelectOneMenu;

public class PrimeFacesFactory {

    public PrimeFaces create() {
        return PrimeFacesImpl.builder()
                .factory(InputText.class, DefaultInputText::new)
                .factory(CommandButton.class, DefaultCommandButton::new)
                .factory(SelectOneMenu.class, DefaultSelectOneMenu::new)
                .build();
    }
}
