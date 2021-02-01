package org.acme.servlet.dynamicroutes;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * This route has no <code>@Route</code> annotation - instead it is registered
 * dynamically by {@link DynamicRouteRegistratorBean}.
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class DynamicRoute extends VerticalLayout {
    public DynamicRoute() {
        add(new Span("Dynamically registered route!"));
    }
}
