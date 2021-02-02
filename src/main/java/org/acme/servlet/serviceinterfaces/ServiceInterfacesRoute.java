package org.acme.servlet.serviceinterfaces;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

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
    }
}
