package org.acme.servlet.pushdemo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;
import org.jetbrains.annotations.Nullable;

/**
 * Demoes Vaadin Push and Websockets.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PageTitle("Vaadin Push Demo | Vaadin Quarkus Demo")
@Route(value = "websocket", layout = MainLayout.class)
public class PushDemoRoute extends VerticalLayout {
    @Nullable
    private volatile Span asyncCounter;
    private Thread thread;

    public PushDemoRoute() {
        add(new Span("Demoes asynchronous websocket push from server. A new value of the span below is pushed every second from the server. You can verify this easily, by opening the browser dev tools, the Networking tab and observing communication running through the ws:// pipe."));
        this.asyncCounter = new Span();
        add(asyncCounter);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        final UI ui = UI.getCurrent();
        thread = new Thread(() -> {
            int value = 0;
            try {
                while (asyncCounter != null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                    value++;
                    final int v = value;
                    if (asyncCounter != null) {
                        ui.access(() -> {
                            if (asyncCounter != null) {
                                asyncCounter.setText("Counter=" + v);
                            }
                        });
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        asyncCounter = null;
        thread.interrupt();
        thread = null;
        super.onDetach(detachEvent);
    }
}
