package io.github.rkeeves.pfsel.pageload;

import io.github.rkeeves.pfsele.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PageLoadTests {

    @Test
    @DisplayName("When new page loads, should be able to check the url")
    void should_be_able_to_check_the_url() {
        Steps.open("https://www.primefaces.org/showcase-v8/ui/menu/panelMenu.xhtml")
                .then(pf -> {
                    pf.$x("//a[text()='Navigations']").click();
                    pf.$x("//span[text()='Links']").click();
                    pf.$x("//span[text()='PrimeFaces']").click();
                    pf.$("a[href='http://www.primefaces.org']").click();
                    pf.urlShouldBeExactly("https://www.primefaces.org/");
                });
    }
}
