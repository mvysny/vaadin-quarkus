package org.acme.servlet;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "about", layout = MainLayout.class)
public class AboutRoute extends VerticalLayout {
    public AboutRoute() {
        add(new Span("A demo app for Vaadin running on top of Quarkus"));
    }
}
