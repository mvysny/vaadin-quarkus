package org.acme.servlet.serviceinterfaces;

import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceEnabled;
import com.urosporo.quarkus.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.flow.i18n.I18NProvider;
import io.quarkus.arc.Unremovable;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Loads resource bundles from src/main/resources/i18n/app*.properties.
 * @author Martin Vysny <mavi@vaadin.com>
 */
@VaadinServiceEnabled
@VaadinServiceScoped
@Unremovable
public class ResourceBundleI18nProvider implements I18NProvider {

    @Override
    public List<Locale> getProvidedLocales() {
        return Arrays.asList(Locale.ENGLISH, Locale.FRENCH, Locale.JAPANESE, new Locale("fi"), new Locale("sk"));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        // Loads resource bundles from src/main/resources/i18n/app*.properties
        final ResourceBundle bundle = ResourceBundle.getBundle("i18n.app", locale);
        return bundle.containsKey(key) ? bundle.getString(key) : "!{" + key + "}";
    }
}
