package org.acme.servlet.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class MainRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(MainRoute.class);
        _assertOne(MainRoute.class);
    }

    @Test
    public void clickingTheButtonGreetsTheUser() {
        _setValue(_get(TextField.class, spec -> spec.withCaption("Your name")), "Martin");
        _click(_get(Button.class, spec -> spec.withCaption("Say hello")));
        expectNotifications("Hello Martin");
    }
}
