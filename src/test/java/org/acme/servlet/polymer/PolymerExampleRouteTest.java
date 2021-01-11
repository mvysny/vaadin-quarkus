package org.acme.servlet.polymer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class PolymerExampleRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(PolymerExampleRoute.class);
    }

    @Test
    public void verifyUserFormPopulation() {
        final UserForm userForm = _get(UserForm.class);
        assertEquals("hello@world.earth", userForm.email.getValue());
        assertEquals("Hello", userForm.firstName.getValue());
        assertEquals("World", userForm.lastName.getValue());
        assertEquals("Hi!", userForm.comment.getValue());
    }

    @Test
    public void modifyingUserFormFieldsNotReflectedToBean() {
        final UserForm userForm = _get(UserForm.class);
        _setValue(userForm.firstName, "Martin");
        assertEquals("Hello", _get(PolymerExampleRoute.class).user.getFirstName());
    }

    @Test
    public void pressingSaveUpdatesBean() {
        final UserForm userForm = _get(UserForm.class);
        _setValue(userForm.firstName, "Martin");
        _click(_get(Button.class, spec -> spec.withCaption("Save")));
        // no notifications => validation succeeds
        expectNotifications("Saved bean: User{email='hello@world.earth', firstName='Martin', lastName='World', comment='Hi!'}");
        assertEquals("Martin", _get(PolymerExampleRoute.class).user.getFirstName());
    }

    @Test
    public void testInvalidValidation() {
        final UserForm userForm = _get(UserForm.class);
        _setValue(userForm.firstName, "");
        _click(_get(Button.class, spec -> spec.withCaption("Save")));
        expectNotifications("Please correct validation errors");
        // nothing got written
        assertEquals("Hello", _get(PolymerExampleRoute.class).user.getFirstName());
    }
}
