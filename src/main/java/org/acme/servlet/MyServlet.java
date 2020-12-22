package org.acme.servlet;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.frontend.FrontendUtils;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@WebServlet(urlPatterns = { "/*" }, initParams = {
        @WebInitParam(name = FrontendUtils.PROJECT_BASEDIR, value = "/home/mavi/work/inacta/code-with-quarkus")
}, asyncSupported = true)
public class MyServlet extends VaadinServlet {
}
