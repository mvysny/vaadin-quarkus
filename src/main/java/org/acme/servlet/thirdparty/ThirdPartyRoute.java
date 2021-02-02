package org.acme.servlet.thirdparty;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;
import org.vaadin.gatanaso.MultiselectComboBox;

/**
 * Demoes a third-party component from Vaadin Directory.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("Third Party Component Integration | Vaadin Quarkus Demo")
@Route(value = "third-party", layout = MainLayout.class)
public class ThirdPartyRoute extends VerticalLayout {
    public ThirdPartyRoute() {
        add(new Paragraph("Demoes a third-party component from Vaadin Directory"));
        MultiselectComboBox<String> multiselectComboBox = new MultiselectComboBox<>();
        multiselectComboBox.setLabel("Select items");
        multiselectComboBox.setItems("Item 1", "Item 2", "Item 3", "Item 4");
        add(multiselectComboBox);
    }
}
