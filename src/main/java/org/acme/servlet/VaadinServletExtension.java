package org.acme.servlet;

import com.vaadin.flow.server.frontend.FrontendUtils;
import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Workaround for https://github.com/mvysny/vaadin-quarkus/issues/2
 * @author Martin Vysny <mavi@vaadin.com>
 */
public class VaadinServletExtension implements ServletExtension {
    @Override
    public void handleDeployment(DeploymentInfo deploymentInfo, ServletContext servletContext) {
        // workaround for https://github.com/mvysny/vaadin-quarkus/issues/2 .
        //
        // In development mode, Vaadin will fail to load the token file
        // (because the token file resides in a separate classloader) and will fail with an exception.
        // In production mode, Vaadin is able to locate the token file and this workaround is not needed.
        //
        // During the development mode, the token file will be located in target/classes/META-INF/VAADIN/config/flow-build-info.json
        // as a file on the filesystem; therefore the resource will successfully resolve to a File.
        // During the production mode, the token file will be located in a jar file, thus will
        // fail to resolve to a File and "tokenFile" will be null. That is fine, since
        // in production mode the workaround is not needed.
        final File tokenFile = getResourceAsFile("META-INF/VAADIN/config/flow-build-info.json");
        if (tokenFile != null) {
            log.info("Token file found on filesystem, forcing Vaadin to use it: " + tokenFile);
            // Force Vaadin to load the token file in dev mode explicitly from given file.
            // workaround for https://github.com/mvysny/vaadin-quarkus/issues/2
            servletContext.setInitParameter(FrontendUtils.PARAM_TOKEN_FILE, tokenFile.getAbsolutePath());
        } else {
            log.info("Token file loaded from jar file; probably Vaadin running in production mode, not patching Vaadin");
        }
    }

    /**
     * Tries to resolve given classpath resource as a file on the filesystem.
     * Fails if the resource is missing from classpath.
     * @param resource the classpath resource, not null.
     * @return a file pointing towards the resource or null if the resource is not a file on the filesystem, but
     * e.g. packed in a jar file.
     */
    private static File getResourceAsFile(String resource) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        Objects.requireNonNull(url, resource + " not found on classpath. Please run 'mvn clean package' once, to generate Vaadin token file.");
        try {
            return Paths.get(url.toURI()).toFile();
        } catch (FileSystemNotFoundException ex) {
            // url is not a file:// URL
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Logger log = LoggerFactory.getLogger(VaadinServletExtension.class);
}
