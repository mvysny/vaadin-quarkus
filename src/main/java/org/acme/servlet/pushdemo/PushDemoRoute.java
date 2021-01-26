package org.acme.servlet.pushdemo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.acme.servlet.MainLayout;

/**
 * Demoes Vaadin Push and Websockets.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@Route(value = "websocket", layout = MainLayout.class)
public class PushDemoRoute extends VerticalLayout {
    private volatile Span asyncCounter;

    public PushDemoRoute() {
        add(new Span("Demoes asynchronous websocket push from server. A new value of the span below is pushed every second from the server. You can verify this easily, by opening the browser dev tools, the Networking tab and observing communication running through the ws:// pipe."));
        this.asyncCounter = new Span();
        add(asyncCounter);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        final UI ui = UI.getCurrent();
        new Thread(() -> {
            int value = 0;
            try {
                while (asyncCounter != null) {
                    Thread.sleep(1000);
                    value++;
                    final int v = value;
                    ui.access(() -> {
                        if (asyncCounter != null) {
                            asyncCounter.setText("Counter=" + v);
                        }
                    });
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        asyncCounter = null;
        super.onDetach(detachEvent);
    }
}
