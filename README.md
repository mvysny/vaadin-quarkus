# Vaadin+Quarkus example project

> Work on this project has been sponsored by [Inacta AG](https://inacta.ch).

This project runs [Vaadin 14](https://vaadin.com/) on top of Quarkus: https://quarkus.io/ .
The JBoss Undertow Servlet container is used to run Vaadin Servlet.

THIS IS A WORK IN PROGRESS - there are a couple of rough edges here and there. After
you check out the project, you need to do the following before
you attempt to run this project:

1. Edit the `web.xml` file and change
   the `project.basedir` configuration option to the correct path to this example project.
   See [Issue #2](https://github.com/mvysny/vaadin-quarkus/issues/2) for more details.
   (note that this is not needed for Vaadin 19+Quarkus, see the v19 branch for more details).

## Demoed Vaadin features

Support for the following features is demoed in this project:

1. Route and RouterLayout auto-discovery: see the `AboutRoute`, `MainRoute` and `PolymerExampleRoute` example route classes
   and the `MainLayout` example layout class.
2. The PolymerTemplate-based components and forms: the `HelloWorld` component
   demoes a reusable PolymerTemplate-based component with a custom TemplateModel, while `UserForm` demoes
   a PolymerTemplate-based form with fields exposed to the server-side via the `@Id` annotation.
3. A custom `ServletContextListener` which is able to run certain functionality before the app
   starts; see the `Bootstrap` class for more details.
4. Both development and production modes are supported.

Known limitations:

1. It's not possible to use a custom VaadinServlet currently: see [Vaadin #9755](https://github.com/vaadin/flow/issues/9755)
   for more details.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application in production mode

The application can be packaged using:
```shell script
./mvnw package -Pproduction
```
It produces the `code-with-quarkus-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar -Pproduction
```

The application is now runnable using `java -jar target/code-with-quarkus-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

> Unsupported at the moment; to be worked on.

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## How this works

The [Quarkus-Undertow Servlet capabilities](https://quarkus.io/guides/http-reference#servlet-config)
are used to take advantage of the [Undertow](https://undertow.io/) Servlet Container,
in order to run Vaadin Servlet seamlessly in the servlet environment.

Quarkus-Undertow integration needs special magic to auto-discover classes which Vaadin needs
to work properly (e.g. `@NpmPackage`-related classes). Quarkus prefers the [Jandex Index](https://quarkus.io/guides/cdi-reference)
however such index is not available for Vaadin components, see [Issue #3](https://github.com/mvysny/vaadin-quarkus/issues/3)
for more details. Workaround is to enable Quarkus class auto-discovery; see the `application.properties`
file for more details.

The `web.xml` file is needed to work around [Issue #2](https://github.com/mvysny/vaadin-quarkus/issues/2).
