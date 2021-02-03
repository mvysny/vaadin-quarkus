package org.acme.servlet.lit;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

/**
 * Demoes that LitElement and LitTemplate-based components and forms works correctly on
 * top of Quarkus.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("Lit Template Demo | Vaadin Quarkus Demo")
@Route(value = "lit", layout = MainLayout.class)
public class LitExampleRoute extends VerticalLayout {
    public LitExampleRoute() {
        add(new Paragraph("A LitElement component demo"));
        add(new H2("Basic LitElement firing events to the server-side"));
        add(new SimpleGreeting());
    }
}
