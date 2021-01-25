package org.acme.servlet.di;

import com.urosporo.quarkus.vaadin.cdi.annotation.NormalRouteScoped;

import java.io.Serializable;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@NormalRouteScoped
public class MyRouteScopedService implements Serializable {
    public String sayHello() {
        return "Hello from " + this;
    }
}
