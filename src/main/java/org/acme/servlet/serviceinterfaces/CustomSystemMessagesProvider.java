package org.acme.servlet.serviceinterfaces;

import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceEnabled;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.flow.server.CustomizedSystemMessages;
import com.vaadin.flow.server.SystemMessages;
import com.vaadin.flow.server.SystemMessagesInfo;
import com.vaadin.flow.server.SystemMessagesProvider;
import io.quarkus.arc.Unremovable;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@VaadinServiceEnabled
@VaadinServiceScoped
@Unremovable
public class CustomSystemMessagesProvider implements SystemMessagesProvider {
    @Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
        final CustomizedSystemMessages messages = new CustomizedSystemMessages();
        messages.setCookiesDisabledMessage("Please enable cookies and try again :)");
        return messages;
    }
}
