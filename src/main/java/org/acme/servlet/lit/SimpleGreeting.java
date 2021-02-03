package org.acme.servlet.lit;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.dom.DomEventListener;

/**
 * Demoes a very simple LitElement. The element fires a <code>say-hello</code>
 * event once the button is clicked. The event is captured by the server-side code,
 * which then sets the <code>greeting<code> property of the client-side component.
 * Lit will then make sure to display the new value of the property.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Tag("simple-greeting")
@NpmPackage(value = "@polymer/paper-input", version = "3.0.2")
@JsModule("./src/lit/simple-greeting.ts")
public class SimpleGreeting extends Component {
    public SimpleGreeting() {
        getElement().addEventListener("say-hello", (DomEventListener) event -> {
            getElement().setProperty("greeting", "Hello, " + event.getEventData().getString("event.detail.name"));
        }).addEventData("event.detail.name");
    }
}
