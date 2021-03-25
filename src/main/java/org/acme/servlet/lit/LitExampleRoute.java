package org.acme.servlet.lit;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
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
    // visible for testing
    final UserForm.User user;

    public LitExampleRoute() {
        add(new Paragraph("A LitElement component demo"));
        add(new H2("Basic LitElement firing events to the server-side"));
        add(new SimpleGreeting());

        // demo the @Id annotation working well with PolymerTemplate
        add(new H2("LitTemplate Form With @Id"));
        user = new UserForm.User();
        user.setFirstName("Hello");
        user.setLastName("World");
        user.setEmail("hello@world.earth");
        user.setComment("Hi!");
        final UserForm userForm = new UserForm();
        add(userForm);
        userForm.getBinder().readBean(user);
        add(new Button("Save", e -> {
            if (userForm.getBinder().isValid() && userForm.getBinder().writeBeanIfValid(user)) {
                System.out.println("Saved bean: " + user);
                Notification.show("Saved bean: " + user);
            } else {
                Notification.show("Please correct validation errors");
            }
        }));
    }
}
