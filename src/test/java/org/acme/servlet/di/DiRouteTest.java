package org.acme.servlet.di;

import com.vaadin.flow.component.UI;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;
import static com.github.mvysny.kaributesting.v10.LocatorJ._get;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@QuarkusTest
public class DiRouteTest extends AbstractAppTest {

    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(DiRoute.class);
        _assertOne(DiRoute.class);
    }

    @Test
    public void verifyDiWorks() {
        assertNotNull(_get(DiRoute.class).myService);
    }
}
