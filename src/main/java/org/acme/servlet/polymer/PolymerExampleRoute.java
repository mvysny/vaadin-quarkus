package org.acme.servlet.polymer;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "polymer", layout = MainLayout.class)
public class PolymerExampleRoute extends VerticalLayout {
    public PolymerExampleRoute() {
        add(new Span("A PolymerTemplate component demo"));
        add(new HelloWorld());
    }
}
