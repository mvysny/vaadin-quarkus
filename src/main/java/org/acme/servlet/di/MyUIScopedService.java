package org.acme.servlet.di;

import com.urosporo.quarkus.vaadin.cdi.annotation.NormalUIScoped;

import java.io.Serializable;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@NormalUIScoped
public class MyUIScopedService implements Serializable {
    public String sayHello() {
        return "Hello from " + this;
    }
}
