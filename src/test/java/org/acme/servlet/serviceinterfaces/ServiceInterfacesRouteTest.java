package org.acme.servlet.serviceinterfaces;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.server.VaadinSession;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;
import static com.github.mvysny.kaributesting.v10.NotificationsKt.expectNotifications;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class ServiceInterfacesRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(ServiceInterfacesRoute.class);
        _assertOne(ServiceInterfacesRoute.class);
    }

    @Test
    public void testThrow() {
        CustomErrorHandler.resetCounter(5);
        // in order to propagate the exception to the custom ErrorHandler, we need to click
        // the button via UI.access(). See Karibu-Testing documentation on ErrorHandler for more details.
        UI.getCurrent().access(() -> _click(_get(Button.class, spec -> spec.withCaption("Throw"))));
        MockVaadin.INSTANCE.runUIQueue(true, VaadinSession.getCurrent());
        expectNotifications("An application error #6 occurred, please see application logs for details");
    }
}
