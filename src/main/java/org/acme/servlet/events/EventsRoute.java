package org.acme.servlet.events;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("DI Events Demo | Vaadin Quarkus Demo")
@Route(value = "events", layout = MainLayout.class)
public class EventsRoute extends VerticalLayout implements AfterNavigationObserver {
    @Inject
    EventCaptureBean eventCaptureBean;
    private final Grid<String> eventsGrid = new Grid<>();

    public EventsRoute() {
        add(new Paragraph("Demoes the possibility to capture Vaadin events in a bean"));
        add(new H2("A list of Vaadin-specific events"));

        eventsGrid.addColumn(it -> it).setHeader("Events").setAutoWidth(true);
        add(eventsGrid);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        final List<String> events = eventCaptureBean.getEvents();
        Collections.reverse(events);
        eventsGrid.setItems(events);
    }
}
