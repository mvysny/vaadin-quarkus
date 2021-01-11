package org.acme.servlet.polymer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

/**
 * Demoes PolymerTemplate-based components and forms.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "polymer", layout = MainLayout.class)
public class PolymerExampleRoute extends VerticalLayout {

    // visible for testing
    final UserForm.User user;

    public PolymerExampleRoute() {
        add(new Span("A PolymerTemplate component demo"));
        add(new HelloWorld());

        // demo the @Id annotation working well with PolymerTemplate
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
