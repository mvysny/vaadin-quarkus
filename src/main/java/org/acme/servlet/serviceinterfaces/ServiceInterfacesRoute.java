package org.acme.servlet.serviceinterfaces;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

import java.util.Locale;

/**
 * Demoes Vaadin Service interfaces as described at https://vaadin.com/docs/v14/flow/cdi/tutorial-cdi-service-beans.html .
 *
 * See https://github.com/mvysny/vaadin-quarkus/issues/18 for more details.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("Service Interfaces Demo | Vaadin Quarkus Demo")
@Route(value = "service-interfaces", layout = MainLayout.class)
public class ServiceInterfacesRoute extends VerticalLayout {
    public ServiceInterfacesRoute() {
        add(new Paragraph("Demoes Vaadin service interfaces implemented by beans"));

        add(new H2("Error Handler"));
        add(new Paragraph("Pressing the following button will throw an exception; the exception should be handled gracefully by the " + CustomErrorHandler.class.getSimpleName()));
        add(new Button("Throw", e -> { throw new RuntimeException("Simulated"); }));

        add(new H2("System Messages"));
        add(new Paragraph("To test the customized system messages (provided by the "
                + CustomSystemMessagesProvider.class.getSimpleName()
                + " class), simply disable cookies temporarily in your browser, then press F5 to reload this tab."));

        add(new H2("I18n provider"));
        add(new Paragraph("Demoes a custom " + ResourceBundleI18nProvider.class.getSimpleName() + " class. Translated greetings for various languages:"));
        final UnorderedList ul = new UnorderedList();
        add(ul);
        ul.add(new ListItem("Current locale (" + getLocale() + "): " + getTranslation("hello")));
        ul.add(new ListItem("English (default): " + getTranslation("hello", Locale.ENGLISH)));
        ul.add(new ListItem("French: " + getTranslation("hello", Locale.FRENCH)));
        ul.add(new ListItem("Japanese: " + getTranslation("hello", Locale.JAPANESE)));
        ul.add(new ListItem("Finnish: " + getTranslation("hello", new Locale("fi"))));
        ul.add(new ListItem("Slovak: " + getTranslation("hello", new Locale("sk"))));
        ul.add(new ListItem("Korean (unsupported, falls back to default): " + getTranslation("hello", Locale.KOREAN)));
    }
}
