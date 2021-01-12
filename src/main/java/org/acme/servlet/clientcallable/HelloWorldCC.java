package org.acme.servlet.clientcallable;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demoes {@link ClientCallable}.
 */
@Tag("hello-world-cc")
@NpmPackage(value = "@polymer/paper-input", version = "3.0.2")
@JsModule("./src/hello-world-cc.js")
public class HelloWorldCC extends Component {
    @ClientCallable
    private String getGreeting(String name) {
        log.info("Hello " + name);
        return "Hello " + name;
    }

    private static final Logger log = LoggerFactory.getLogger(HelloWorldCC.class);
}
