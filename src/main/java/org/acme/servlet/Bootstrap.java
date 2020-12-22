package org.acme.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
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
