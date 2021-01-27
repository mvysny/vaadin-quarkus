package org.acme.servlet;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.acme.servlet.about.AboutRoute;
import org.acme.servlet.clientcallable.ClientCallableRoute;
import org.acme.servlet.di.DiRoute;
import org.acme.servlet.main.MainRoute;
import org.acme.servlet.polymer.PolymerExampleRoute;
import org.acme.servlet.pushdemo.PushDemoRoute;
import org.acme.servlet.thirdparty.ThirdPartyRoute;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Martin Vysny <mavi@vaadin.com>
 */
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/shared-styles.css")
@Push
public class MainLayout extends AppLayout implements RouterLayout, AfterNavigationObserver {

    private final H4 currentViewName = new H4();

    private static final Map<Class<? extends Component>, String> routes = new LinkedHashMap<>();
    static {
        routes.put(MainRoute.class, "Welcome");
        routes.put(PolymerExampleRoute.class, "Polymer Template Demo");
        routes.put(ClientCallableRoute.class, "Client Callable Demo");
        routes.put(DiRoute.class, "Dependency Injection Demo");
        routes.put(ThirdPartyRoute.class, "Third Party Component");
        routes.put(PushDemoRoute.class, "Push Demo");
        routes.put(AboutRoute.class, "About");
    }

    private final Map<Class<?>, Tab> navigationTabMap = new HashMap<>();
    private final Tabs navigationTabs = new Tabs();

    public MainLayout() {
        setPrimarySection(AppLayout.Section.DRAWER);
        final Div spacer = new Div();
        spacer.setWidth("8px");
        addToNavbar(new DrawerToggle(), new H3("Vaadin Quarkus Demo"), spacer, currentViewName);

        navigationTabs.setOrientation(Tabs.Orientation.VERTICAL);
        for (Map.Entry<Class<? extends Component>, String> route : routes.entrySet()) {
            final Tab tab = new Tab(new RouterLink(route.getValue(), route.getKey()));
            navigationTabMap.put(route.getKey(), tab);
            navigationTabs.add(tab);
        }
        addToDrawer(navigationTabs);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        final Class<?> routeClass = event.getActiveChain().get(0).getClass();
        final String routeCaption = routes.get(routeClass);
        if (routeCaption == null) {
            currentViewName.setText("");
        } else {
            currentViewName.setText("/ " + routeCaption);
        }

        final Tab currentTab = navigationTabMap.get(routeClass);
        navigationTabs.setSelectedTab(currentTab);
    }
}
