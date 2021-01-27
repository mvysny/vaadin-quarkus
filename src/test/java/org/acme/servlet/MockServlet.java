package org.acme.servlet;

import com.urosporo.quarkus.vaadin.cdi.QuarkusVaadinServlet;
import com.urosporo.quarkus.vaadin.cdi.QuarkusVaadinServletService;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinServletService;
import io.quarkus.test.Mock;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Disables atmosphere.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Mock
public class MockServlet extends QuarkusVaadinServlet {
    @Inject
    BeanManager beanManager;

    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration configuration) throws ServiceException {
        final QuarkusVaadinServletService service = new MockService(this, configuration, this.beanManager);
        service.init();
        return service;
    }

    public static class MockService extends QuarkusVaadinServletService {

        public MockService(QuarkusVaadinServlet servlet, DeploymentConfiguration configuration, BeanManager beanManager) {
            super(servlet, configuration, beanManager);
        }

        @Override
        protected boolean isAtmosphereAvailable() {
            return false;
        }
    }
}
