package org.acme.servlet.polymer;

import com.vaadin.flow.component.UI;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
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
}
