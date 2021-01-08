package org.acme.servlet.polymer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
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

        // demo the @Id annotation working well with PolymerTemplate
        final UserForm.User user = new UserForm.User();
        user.setFirstName("Hello");
        user.setLastName("World");
        user.setEmail("hello@world.earth");
        user.setComment("Hi!");
        final UserForm userForm = new UserForm();
        add(userForm);
        userForm.setBean(user);
        add(new Button("Save", e -> {
            userForm.setBean(user);
            System.out.println("Saved bean: " + user);
            Notification.show("Saved bean: " + user);
        }));
    }
}
