package org.acme.servlet;

import com.github.mvysny.kaributesting.v10.mock.MockInstantiatorV18;
import com.github.mvysny.kaributesting.v10.mock.MockNpmTemplateParser;
import com.urosporo.quarkus.vaadin.cdi.QuarkusInstantiator;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceEnabled;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.flow.component.littemplate.LitTemplateParser;
import com.vaadin.flow.component.polymertemplate.TemplateParser;
import io.quarkus.test.Mock;

/**
 * Forces Vaadin to use {@link MockNpmTemplateParser}.
 */
@VaadinServiceScoped
@VaadinServiceEnabled
@Mock
public class MockInstantiator extends QuarkusInstantiator {
    @Override
    public <T> T getOrCreate(Class<T> type) {
        if (type == LitTemplateParser.LitTemplateParserFactory.class) {
            return new MockInstantiatorV18(this).getOrCreate(type);
        }
        return super.getOrCreate(type);
    }
}
