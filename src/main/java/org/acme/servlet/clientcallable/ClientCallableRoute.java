package org.acme.servlet.clientcallable;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

/**
 * Demoes {@link ClientCallable}.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "cc", layout = MainLayout.class)
public class ClientCallableRoute extends VerticalLayout {
    public ClientCallableRoute() {
        add(new HelloWorldCC());
    }
}
