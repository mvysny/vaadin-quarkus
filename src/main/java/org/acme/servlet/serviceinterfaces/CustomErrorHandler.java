package org.acme.servlet.serviceinterfaces;

import com.helger.commons.annotation.VisibleForTesting;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceEnabled;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import io.quarkus.arc.Unremovable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@VaadinServiceEnabled
@VaadinServiceScoped
@Unremovable
public class CustomErrorHandler implements ErrorHandler {
    private static final AtomicInteger ERROR_ID = new AtomicInteger();
    private static final Logger log = LoggerFactory.getLogger(CustomErrorHandler.class);

    @VisibleForTesting
    static void resetCounter(int id) {
        ERROR_ID.set(id);
    }

    @Override
    public void error(ErrorEvent event) {
        final int id = ERROR_ID.incrementAndGet();
        log.error("Application error #" + id, event.getThrowable());

        // the handler can also be called from Servlet after the current instances have been cleared.
        // Calling Notification.show() will fail if UI.getCurrent() is null. Check for this case.
        if (UI.getCurrent() != null) {
            Notification.show("An application error #" + id + " occurred, please see application logs for details",
                    5000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR, NotificationVariant.LUMO_PRIMARY);
        }
    }
}
