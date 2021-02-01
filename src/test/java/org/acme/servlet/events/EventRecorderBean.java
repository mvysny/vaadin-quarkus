package org.acme.servlet.events;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.EventObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@ApplicationScoped
public class EventRecorderBean {
    private final Map<Class<? extends EventObject>, EventObject> events = new ConcurrentHashMap<>();

    public void clear() {
        events.clear();
    }

    @Nullable
    public <T extends EventObject> T getEvent(@NotNull Class<T> eventClass) {
        //noinspection unchecked
        return (T) events.get(eventClass);
    }

    void onUIInitEvent(@Observes UIInitEvent e) {
        addEvent(e);
    }

    void onSessionInitEvent(@Observes SessionInitEvent e) {
        addEvent(e);
    }

    void onSessionDestroyEvent(@Observes SessionDestroyEvent e) {
        addEvent(e);
    }

    void onServiceInitEvent(@Observes ServiceInitEvent e) {
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

    private void addEvent(@NotNull EventObject e) {
        events.put(e.getClass(), e);
    }
}
