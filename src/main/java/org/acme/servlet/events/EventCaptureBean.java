package org.acme.servlet.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.PollEvent;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.*;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread-safe.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@ApplicationScoped
public class EventCaptureBean {
    /**
     * Lists all events, from the oldest to the newest.
     */
    private final Collection<String> events = new LinkedBlockingQueue<>();

    void onUIInitEvent(@Observes UIInitEvent e) {
        addEvent(e);
    }

    void onSessionInitEvent(@Observes SessionInitEvent e) {
        addEvent(e);
    }

    void onSessionDestroyEvent(@Observes SessionDestroyEvent e) {
        addEvent(e);
    }

    void onServiceDestroyEvent(@Observes ServiceDestroyEvent e) {
        addEvent(e);
    }

    void onAfterNavigationEvent(@Observes AfterNavigationEvent e) {
        addEvent(e);
    }

    void onBeforeEnterEvent(@Observes BeforeEnterEvent e) {
        addEvent(e);
    }

    void onBeforeLeaveEvent(@Observes BeforeLeaveEvent e) {
        addEvent(e);
    }

    void onPollEvent(@Observes PollEvent e) {
        addEvent(e);
    }

    private void addEvent(@NotNull EventObject e) {
        events.add(LocalTime.now() + ": " + toString(e));
    }

    @NotNull
    private static String toString(@NotNull EventObject e) {
        // filed as https://github.com/vaadin/flow/issues/9916
        final StringBuilder sb = new StringBuilder();
        sb.append(e.toString());
        if (e instanceof UIInitEvent) {
            sb.append(", UI=").append(((UIInitEvent) e).getUI());
        } else if (e instanceof SessionInitEvent) {
            sb.append(", Session=").append(((SessionInitEvent) e).getSession());
            sb.append(", Request=").append(((VaadinServletRequest) ((SessionInitEvent) e).getRequest()).getRequestURL());
        } else if (e instanceof SessionDestroyEvent) {
            sb.append(", Session=").append(((SessionDestroyEvent) e).getSession());
        } else if (e instanceof ServiceDestroyEvent) {
            // no additional info
        } else if (e instanceof AfterNavigationEvent) {
            sb.append(", location=").append(toString(((AfterNavigationEvent) e).getLocation()));
        } else if (e instanceof BeforeEvent) {
            sb.append(", location=").append(toString(((BeforeEvent) e).getLocation()));
            sb.append(", trigger=").append(((BeforeEvent) e).getTrigger());
        } else if (e instanceof ComponentEvent) {
            // handles PollEvent
            sb.append(", isFromClient=").append(((ComponentEvent<?>) e).isFromClient());
        }
        return sb.toString();
    }

    @NotNull
    private static String toString(@NotNull Location location) {
        return location.getPathWithQueryParameters();
    }

    /**
     * Returns a snapshot of the current events, from the oldest to the newest.
     */
    @NotNull
    public List<String> getEvents() {
        return new ArrayList<>(events);
    }
}
