package org.acme.servlet.lit;

import com.vaadin.flow.component.UI;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.acme.servlet.polymer.PolymerExampleRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

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
}
