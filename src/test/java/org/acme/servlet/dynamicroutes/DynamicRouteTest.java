package org.acme.servlet.dynamicroutes;

import com.vaadin.flow.component.UI;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class DynamicRouteTest extends AbstractAppTest {
    @Test
    public void smokeTest() {
        UI.getCurrent().navigate(DynamicRoute.class);
        _assertOne(DynamicRoute.class);
    }
}
