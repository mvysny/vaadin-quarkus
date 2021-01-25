package org.acme.servlet;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.mock.MockedUI;
import com.urosporo.quarkus.vaadin.cdi.QuarkusVaadinServlet;
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
    QuarkusVaadinServlet servlet;

    @BeforeEach
    public void mockVaadin() {
        MockVaadin.setup(MockedUI::new, servlet);
    }

    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }
}
