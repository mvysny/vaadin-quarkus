package org.acme.servlet.lit;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class LitExampleRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(LitExampleRoute.class);
        _assertOne(LitExampleRoute.class);
    }

    @Test
    public void smoke() {
        _assertOne(SimpleGreeting.class);
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
        assertEquals("Hello", _get(LitExampleRoute.class).user.getFirstName());
    }

    @Test
    public void pressingSaveUpdatesBean() {
        final UserForm userForm = _get(UserForm.class);
        _setValue(userForm.firstName, "Martin");
        _click(_get(Button.class, spec -> spec.withCaption("Save")));
        // no notifications => validation succeeds
        expectNotifications("Saved bean: User{email='hello@world.earth', firstName='Martin', lastName='World', comment='Hi!'}");
        assertEquals("Martin", _get(LitExampleRoute.class).user.getFirstName());
    }

    @Test
    public void testInvalidValidation() {
        final UserForm userForm = _get(UserForm.class);
        _setValue(userForm.firstName, "");
        _click(_get(Button.class, spec -> spec.withCaption("Save")));
        expectNotifications("Please correct validation errors");
        // nothing got written
        assertEquals("Hello", _get(LitExampleRoute.class).user.getFirstName());
    }
}
