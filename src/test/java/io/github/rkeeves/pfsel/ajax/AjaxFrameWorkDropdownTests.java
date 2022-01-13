package io.github.rkeeves.pfsel.ajax;

import com.codeborne.selenide.SelenideElement;
import io.github.rkeeves.pfsele.PrimeFaces;
import io.github.rkeeves.pfsele.Steps;
import io.github.rkeeves.pfsele.page.PrimeFacesDecorator;
import io.github.rkeeves.pfsele.widget.commandbutton.CommandButton;
import io.github.rkeeves.pfsele.widget.selectonemenu.SelectOneMenu;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static org.openqa.selenium.By.id;

class AjaxFrameWorkDropdownTests {

    @Test
    @DisplayName("When setting a selectOneMenu causes ajax update, it should implicitly wait for ajax to finish, no page obejct")
    void should_be_able_to_wait_for_ajax_no_page_object() {
        Steps.open("https://www.primefaces.org/showcase-v8/ui/ajax/dropdown.xhtml")
                .then(pf -> {
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:country")).setValue("USA");
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:city")).setValue("Denver");
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:country")).setValue("Brazil");
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:city")).setValue("Sao Paulo");
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:country")).setValue("Germany");
                    pf.get(SelectOneMenu.class)
                            .by(id("j_idt718:city")).setValue("Berlin");
                    pf.get(CommandButton.class)
                            .by(id("j_idt718:j_idt728")).click();
                    pf.$(id("j_idt718:msgs_container")).shouldBe(visible);
                });
    }

    @RequiredArgsConstructor
    static class DropDownPageSimple implements PrimeFacesDecorator {

        @Getter
        private final PrimeFaces primeFaces;

        public SelectOneMenu country() {
            return primeFaces.get(SelectOneMenu.class).by(id("j_idt718:country"));
        }

        public SelectOneMenu city() {
            return primeFaces.get(SelectOneMenu.class).by(id("j_idt718:city"));
        }

        public CommandButton submit() {
            return primeFaces.get(CommandButton.class).by(id("j_idt718:j_idt728"));
        }

        public SelenideElement growl() {
            return primeFaces.$(id("j_idt718:msgs_container"));
        }
    }

    @Test
    @DisplayName("When setting a selectOneMenu causes ajax update, it should implicitly wait for ajax to finish, simple page object")
    void should_be_able_to_wait_for_ajax_with_simple_page_object() {
        Steps.open("https://www.primefaces.org/showcase-v8/ui/ajax/dropdown.xhtml")
                .then(DropDownPageSimple::new, dropDownPage -> {
                    dropDownPage.country().setValue("USA");
                    dropDownPage.city().setValue("Denver");
                    dropDownPage.country().setValue("Brazil");
                    dropDownPage.city().setValue("Sao Paulo");
                    dropDownPage.country().setValue("Germany");
                    dropDownPage.city().setValue("Berlin");
                    dropDownPage.submit().click();
                    dropDownPage.growl().shouldBe(visible);
                });
    }

    @RequiredArgsConstructor
    static class DropDownPageEncapsulating implements PrimeFacesDecorator {

        @Getter
        private final PrimeFaces primeFaces;

        public DropDownPageEncapsulating fill(final String countryValue, final String cityValue) {
            country().setValue(countryValue);
            city().setValue(cityValue);
            return this;
        }

        public void submitSuccessfully() {
            submit().click();
            growl().shouldBe().shouldBe(visible);
        }

        private SelectOneMenu country() {
            return primeFaces.get(SelectOneMenu.class).by(id("j_idt718:country"));
        }

        private SelectOneMenu city() {
            return primeFaces.get(SelectOneMenu.class).by(id("j_idt718:city"));
        }

        private CommandButton submit() {
            return primeFaces.get(CommandButton.class).by(id("j_idt718:j_idt728"));
        }

        private SelenideElement growl() {
            return primeFaces.$(id("j_idt718:msgs_container"));
        }
    }

    @Test
    @DisplayName("When setting a selectOneMenu causes ajax update, it should implicitly wait for ajax to finish, encapsulating page object")
    void should_be_able_to_wait_for_ajax_with_encapsulating_page_object() {
        Steps.open("https://www.primefaces.org/showcase-v8/ui/ajax/dropdown.xhtml")
                .then(DropDownPageEncapsulating::new, dropDownPage -> {
                    dropDownPage.fill("USA", "Denver");
                    dropDownPage.fill("Brazil", "Sao Paulo");
                    dropDownPage.fill("Germany", "Berlin");
                    dropDownPage.submitSuccessfully();
                });
    }
}
