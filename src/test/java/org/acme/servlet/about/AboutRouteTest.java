package org.acme.servlet.about;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import org.acme.servlet.AbstractAppTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class AboutRouteTest extends AbstractAppTest {
    @BeforeEach
    public void navigate() {
        UI.getCurrent().navigate(AboutRoute.class);
    }

    @Test
    public void smoke() {
        _assertOne(Span.class);
    }
}
