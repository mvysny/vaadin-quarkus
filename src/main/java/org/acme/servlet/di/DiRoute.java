package org.acme.servlet.di;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

import javax.inject.Inject;

/**
 * Demoes the dependency injection of services.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "di", layout = MainLayout.class)
public class DiRoute extends VerticalLayout {
    @Inject
    MyService myService;
    @Inject
    MySessionScopedService mySessionScopedService;
    @Inject
    MyUIScopedService myUIScopedService;
    @Inject
    MyRouteScopedService myRouteScopedService;

    public DiRoute() {
        add(new H3("Demoes Quarkus dependency injection and various scopes"));
        add(new Span("This route: " + this));
        add(new Span("Warning: pressing F5 will re-create the UI, clearing both the UI-scoped and Route-scoped beans"));
        add(new Hr());
        add(new Span("Prototype-scoped; navigate away and back to get a new instance"));
        add(new Button("Say hello, prototype", e -> Notification.show(myService.sayHello())));
        add(new Span("Session-scoped; remains for the duration of the session and survives refresh via F5"));
        add(new Button("Say hello, session", e -> Notification.show(mySessionScopedService.sayHello())));
        add(new Span("UI-scoped; remains until the current browser tab is closed"));
        add(new Button("Say hello, UI", e -> Notification.show(myUIScopedService.sayHello())));
        add(new Span("Route-scoped; tied to this route (until a new UI is created)"));
        add(new Button("Say hello, route", e -> Notification.show(myRouteScopedService.sayHello())));
    }
}
