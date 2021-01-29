package org.acme.servlet.events;

import com.github.mvysny.kaributesting.v10.GridKt;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.acme.servlet.main.MainRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class EventsRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(EventsRoute.class);
        _assertOne(EventsRoute.class);
    }

    @Test
    public void smoke() {
        _assertOne(Grid.class);
    }

    @Test
    public void testGridPopulated() {
        final Grid<String> eventGrid = _get(Grid.class);
        // there should be at least 4 events logged: session init, ui init, before enter, after enter
        if (GridKt._size(eventGrid)< 4) {
            fail("The grid is not populated properly: " + GridKt._dump(eventGrid));
        }
    }
}
