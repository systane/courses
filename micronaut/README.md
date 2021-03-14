# Micronaut

[Micronaut](https://micronaut.io/) is a modern JVM-based framework designed to build JVM applications with support to Groove, Kotlin and Java. In contrast to Spring, Micronaut tries to avoid:
- Use o reflection;
- Long startup server time;
- No runtime bytecode generation;
- Reduced memory footprint;

Spring was not designed to run in scenarios such as Android app, low memory footprint and serverless.
Although Micronaut, tries to differ from Spring in some aspects, it also implement some benefits, like: Dependency Injection and Inversion of Control (IoC), Sensible Defaults and Auto-Configuration.

Micronaut can achieve all this, with the use o [Java's annotation processors](https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html). These annotation processors precompile the necessary metadata to perform DI, define AOP proxies and configure your application to run in low memory.

## How does it work?
Micronaut is able to perform DI withou reflection, because it uses the annotation processors that generate classes at the compile time. Because of this, the codebase size of your application will not affect the memory consumption in the same way like it happens in Spring. Spring needs to cache reflection data for every field, method and constructor in your code, thus as your code growns in size, so do your memory requirements.

## Startup
You can install the micronaut cli with SDKMAN, Homebrew or Chocolatey. This cli is optional to use the framework, is only helps you to automatize some manual processes, like generating the basic structure of your application, create controllers, and so on. It works in a similiar way like [Artisan CLI](https://laravel.com/docs/5.0/artisan) from Laravel. But you can also use the [Micronaut launch](https://micronaut.io/launch/) to fast generate your application, such as [Spring Initializr](https://start.spring.io/).

After the cli installed, you can simple type `mn create-app hello-world --build maven` to create a simple micronaut maven based application. To start your app, you can simple enter in the folder of your project and run `./mvnw mn:run`. By default, Micronaut runs on port 8080, but you can override this configuration, specifying a new number port in the application.yml file.

You can use the cli to create beans, controllers, etc. For example, we can define a controller:
`mn create-controller Book`.If you want to know more, checkout the [official documentation](https://docs.micronaut.io/latest/guide/index.html#cli).

## Auto Restart

Once Micronaut has a fast startup time, we can use the maven plugin to automatic restart our application when changes are detect in our application files. To do so, you just need to type `./mvnw mn:run` or you're using gradle `./gradlew run -t`