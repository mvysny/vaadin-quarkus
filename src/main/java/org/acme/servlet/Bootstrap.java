package org.acme.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * A demo of a simple ServletContextListener which is able to e.g. prepare
 * database connectivity before the app starts (and tear it down afterwards).
 * @author Martin Vysny <mavi@vaadin.com>
 */
@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Server starting");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server stopping");
    }
}
