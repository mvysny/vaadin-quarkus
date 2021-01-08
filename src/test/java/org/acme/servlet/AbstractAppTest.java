package org.acme.servlet;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests the UI. Uses the Browserless testing approach as provided by the
 * [Karibu Testing](https://github.com/mvysny/karibu-testing) library.
 *
 * @author Martin Vysny <mavi@vaadin.com>
 */
public abstract class AbstractAppTest {
    private static final Routes routes = new Routes().autoDiscoverViews("org.acme.servlet");

    @BeforeAll
    public static void startupApp() {
        new Bootstrap().contextInitialized(null);
    }

    @AfterAll
    public static void tearDownApp() {
        new Bootstrap().contextDestroyed(null);
    }

    @BeforeEach
    public void mockVaadin() {
        MockVaadin.setup(routes);
    }

    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }
}
