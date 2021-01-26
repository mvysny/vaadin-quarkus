package org.acme.servlet;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * Dummy class which causes Quarkus to actually enables websockets, enabling Vaadin+Atmosphere to
 * work correctly. See https://github.com/mvysny/vaadin-quarkus/issues/12 for more details.
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class EnableWebsockets implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        return new HashSet<>();
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return new HashSet<>();
    }
}
