package org.acme.servlet.polymer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Demoes that PolymerTemplate-based components and forms works correctly on
 * top of Quarkus.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("Polymer Template Demo | Vaadin Quarkus Demo")
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

        // demo a PolymerTemplate-based form
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

        // demo a dom-repeat
        final List<UserForm.User> users = new ArrayList<>();
        users.add(new UserForm.User("jd@foo.bar", "John", "D"));
        users.add(new UserForm.User("janed@foo.bar", "Jane", "D"));
        users.add(new UserForm.User("miked@foo.bar", "Mike", "D"));
        final UserTable userTable = new UserTable();
        userTable.setUsers(users);
        add(userTable);
    }
}
