package org.acme.servlet.events;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.SessionInitEvent;
import com.vaadin.flow.server.UIInitEvent;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests that the events are fired properly.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class EventFireTest extends AbstractAppTest {

    @Inject
    private EventRecorderBean eventRecorder;

    @AfterEach
    public void clearRecordedEvents() {
        eventRecorder.clear();
    }

    @Override @BeforeEach
    public void mockVaadin() {
        System.out.println("HAHA KOKOS");
        // make sure to clear the events before we mock/init Vaadin, since that will fire events which we want to capture.
        eventRecorder.clear();
        super.mockVaadin();
    }

    @Test
    public void testEventsFired() {
        // Vaadin initialized properly
        assertNotNull(eventRecorder.getEvent(ServiceInitEvent.class));
        assertNotNull(eventRecorder.getEvent(SessionInitEvent.class));
        assertNotNull(eventRecorder.getEvent(UIInitEvent.class));
        // Karibu-Testing also navigates to the main route
        assertNotNull(eventRecorder.getEvent(BeforeEnterEvent.class));
        assertNotNull(eventRecorder.getEvent(AfterNavigationEvent.class));
    }

    @Test
    public void testNavigation() {
        eventRecorder.clear();
        UI.getCurrent().navigate(EventsRoute.class);
        _assertOne(EventsRoute.class);
        // now the navigation has been performed, we can check that the events have been fired.
        assertNotNull(eventRecorder.getEvent(BeforeEnterEvent.class));
        assertNotNull(eventRecorder.getEvent(AfterNavigationEvent.class));
    }
}
