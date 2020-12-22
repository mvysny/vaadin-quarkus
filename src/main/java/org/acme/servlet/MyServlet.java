package org.acme.servlet;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.frontend.FrontendUtils;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@WebServlet(urlPatterns = { "/*" }, initParams = {
        // workaround for https://github.com/mvysny/vaadin-quarkus/issues/2
        @WebInitParam(name = FrontendUtils.PROJECT_BASEDIR, value = "/home/mavi/work/inacta/vaadin-quarkus")
}, asyncSupported = true)
public class MyServlet extends VaadinServlet {
}
