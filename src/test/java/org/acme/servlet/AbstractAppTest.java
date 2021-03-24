package org.acme.servlet;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.mock.MockedUI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;

/**
 * Tests the UI. Uses the Browserless testing approach as provided by the
 * [Karibu Testing](https://github.com/mvysny/karibu-testing) library.
 *
 * @author Martin Vysny <mavi@vaadin.com>
 */
public abstract class AbstractAppTest {
    @Inject
    MockServlet servlet;

    @BeforeEach
    public void mockVaadin() {
        // if this fails with
        // java.lang.NullPointerException: Parameter specified as non-null is null: method com.github.mvysny.kaributesting.v10.MockVaadin.setup, parameter servlet
        //
        // make sure your test class is annotated with @QuarkusTest
        MockVaadin.setup(MockedUI::new, servlet);
    }

    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }
}
