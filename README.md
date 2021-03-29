# Vaadin+Quarkus example project

> Work on this project has been kindly sponsored by [Inacta AG](https://inacta.ch).

This project runs [Vaadin 14](https://vaadin.com/) on top of [Quarkus](https://quarkus.io/).
The [JBoss Undertow Servlet container](https://undertow.io/) is used to run the Vaadin Servlet.
Vaadin 19 port is also available, please see the `v19` branch.

Uses [Vaadin Quarkus Extension](https://github.com/urosporo/vaadin-quarkus-extension-parent)
to run smoothly on top of Quarkus.

## Demoed Vaadin features

Support for the following features is demoed in this project:

1. Route and RouterLayout auto-discovery: see the `AboutRoute`, `MainRoute` and `PolymerExampleRoute` example route classes
   and the `MainLayout` example layout class.
2. The PolymerTemplate-based components and forms: the `HelloWorld` component
   demoes a reusable `PolymerTemplate`-based component with a custom `TemplateModel`, while `UserForm` demoes
   a `PolymerTemplate`-based form with fields exposed to the server-side via the `@Id` annotation.
3. Form validation via JSR-303, `BeanValidationBinder` and [hibernate-validator](https://hibernate.org/validator/).
   See `UserForm`.
4. A custom `ServletContextListener` which is able to run certain functionality before the app
   starts; see the `Bootstrap` class for more details.
5. Both development and production modes are supported.
6. Fast testing with [Karibu-Testing](https://github.com/mvysny/karibu-testing).
   See `src/test/java` for more details.
7. Dependency injection is supported: you can inject beans into Vaadin `@Route`-annotated
   views. See the `DiRoute` for more details.
8. Adding third-party components from [Vaadin Directory](https://vaadin.com/directory) works.
   See the `ThirdPartyRoute`.
9. Vaadin Push - the `PushDemoRoute`.
10. Bean events - demoes Vaadin-specific bean events being fired. See `EventsRoute`
   for more details.
11. `@ClientCallable` - see `ClientCallableRoute`
12. `LitTemplate`: The `HelloWorld` component
   demoes a LitElement-based component with events and properties, while
    `UserForm` demoes a `LitTemplate`-based for with fields exposed to the server-side via the `@Id` annotation.
    Also a `LitElement`-based component is demoed.
13. Routes registered dynamically in the `ServiceInitEvent` observer bean.
14. Vaadin-related event observers: `ServiceInitEvent`, `SessionInitEvent`, `UIInitEvent`,
    navigation events, etc.
15. Custom Vaadin service interfaces: custom `ErrorHandler`, custom `I18nProvider`,
    custom `SystemMessagesProvider`.

Known limitations:

1. It's not possible to use a custom app-specific VaadinServlet currently:
   see [Vaadin #9755](https://github.com/vaadin/flow/issues/9755)
   for more details. However, the Vaadin-Quarkus Extension uses a custom
   `QuarkusVaadinServlet`.

## Demoed Vaadin Versions

There are three branches of this example project:

1. The `main` branch tracking the newest Vaadin 14.5.1 version
2. The `v19` branch tracking the Vaadin 19 version

## Vaadin Quickstarter / Archetype

For a very simple Skeleton Starter app which can also serve as an archetype app,
please see [vaadin-quarkus-skeleton-starter](https://github.com/mvysny/vaadin-quarkus-skeleton-starter).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw -C clean compile quarkus:dev
```

## Packaging and running the application in production mode

The application can be packaged using:
```shell script
./mvnw -C clean package -Pproduction
```
It produces the `code-with-quarkus-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw -C clean package -Dquarkus.package.type=uber-jar -Pproduction
```

The application is now runnable using `java -jar target/code-with-quarkus-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

Unsupported.

## How this works

The [Quarkus-Undertow Servlet capabilities](https://quarkus.io/guides/http-reference#servlet-config)
are used to take advantage of the [Undertow](https://undertow.io/) Servlet Container,
in order to run Vaadin Servlet seamlessly in the servlet environment.

Quarkus-Undertow integration needs special magic to auto-discover classes which Vaadin needs
to work properly (e.g. `@NpmPackage`-annotated classes). Quarkus uses the [Jandex Index](https://quarkus.io/guides/cdi-reference);
the Jandex Index is available for Vaadin at [vaadin-jandex](https://github.com/mvysny/vaadin-jandex/)
for more details.

## Adding Third-party components

In order for Vaadin to correctly discover `@NpmModule`-annotated classes
for third-party components added from Vaadin Directory, they need to package the
Jandex index. Often they don't, therefore it's important to take advantage
of `quarkus.index-dependency` and add those third-party
components to `application.properties`.

For example, in order to use the [Multiselect ComboBox](https://github.com/gatanaso/multiselect-combo-box-flow),
you need to add the following lines to your `application.properties`:

```
quarkus.index-dependency.multiselect-combo-box-flow.group-id=org.vaadin.gatanaso
quarkus.index-dependency.multiselect-combo-box-flow.artifact-id=multiselect-combo-box-flow
```

# License

See [LICENSE](LICENSE) for more details.
