package org.acme.servlet;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/shared-styles.css")
@Push
public class AppShell implements AppShellConfigurator {
}
