package org.acme.servlet;

import com.github.mvysny.kaributesting.v10.mock.MockNpmTemplateParser;
import com.urosporo.quarkus.vaadin.cdi.QuarkusInstantiator;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceEnabled;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.flow.component.polymertemplate.TemplateParser;
import io.quarkus.arc.Unremovable;
import io.quarkus.test.Mock;

/**
 * Forces Vaadin to use {@link MockNpmTemplateParser}.
 */
@VaadinServiceScoped
@VaadinServiceEnabled
@Unremovable
@Mock
public class MockInstantiator extends QuarkusInstantiator {
    @Override
    public TemplateParser getTemplateParser() {
        return new MockNpmTemplateParser();
    }
}
