package org.acme.servlet;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/shared-styles.css")
public class MainLayout extends VerticalLayout implements RouterLayout {
    public MainLayout() {
        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(new HorizontalLayout(new RouterLink("Welcome", WelcomeRoute.class), new RouterLink("About", AboutRoute.class)));
    }
}
