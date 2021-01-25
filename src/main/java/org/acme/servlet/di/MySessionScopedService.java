package org.acme.servlet.di;

import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinSessionScoped;

import java.io.Serializable;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@VaadinSessionScoped
public class MySessionScopedService implements Serializable {
    public String sayHello() {
        return "Hello from " + this;
    }
}
