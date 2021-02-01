package org.acme.servlet.dynamicroutes;

import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import org.acme.servlet.MainLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * Demoes dynamic route registration. Please see https://vaadin.com/docs/v14/flow/routing/tutorial-router-dynamic-routes.html
 * for more details.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@ApplicationScoped
public class DynamicRouteRegistratorBean {
    private static final Logger log = LoggerFactory.getLogger(DynamicRouteRegistratorBean.class);
    void onServiceInitialized(@Observes ServiceInitEvent event) {
        RouteConfiguration configuration = RouteConfiguration.forApplicationScope();
        configuration.setRoute("dynamic", DynamicRoute.class, MainLayout.class);
        log.info("Dynamic route " + DynamicRoute.class.getSimpleName() + " has been registered under /dynamic");
    }
}
