# **Spring Framework**
Spring is a framework to web development, and it has many features that we'll be talking in this article.

**Ioc Containers:** The Spring container is the core of the Spring Framework. Containers will create, configure, wire and manage objects their entire life cycle. With the DI (Dependency Inversion) spring can manage this objects (Spring Beans) easily.

The container gets its instructions of what Beans to instantiate, configure and assemble by reading the configuration metadata provided. This configuration can be Java annotation, XML or Java code.

There are two of types of Spring containers:

- *Spring BeanFactory Container:* 
  Is the simplest container in the Spring Framework and it still present just for the sake of backward compatibility with other frameworks that integrate with Spring.

- *Spring ApplicationContext Container:* 
  This is the most modern Spring container with ability to resolve textual messages from a properties file and other functionalities specialy the Spring BeanFactory stuff.

**Beans:** they are the objects that form the backbone of your application. They're managed by the Spring Ioc Container (Spring Context). 

Beans have some important properties like the **scope**. This attribute defines how Spring Context will instance a new bean each time one is needed. If you need only one instance of a bean, you should define the scope as **singleton** (default scope), you need a new instance on each request you should use a **request** scope. in addition to that, you can also configure methods to execute before the destruction or after the initialization of a bean or a group of beans. If all this functionalities doens't fit, you can entirely customize the instantiation logic, dependency-resolution logic just defining BeanPostProcessor implementations.

**Dependency Injection:** Helps you in gluing classes together and the same time keeping them independent and the entire procedure of DI is controlled by the Spring Framework. In the bellow code the Computer class knows how the Screen class is build and all its dependencies (if the screen have depended of other class, the computer class will be know).

    public class Computer {
      private Screen screen;
      
      public Computer() {
        screen = new Screen();
      }
    }


But in the bellow piece of code, the Computer class know nothing about the Screen, and that's Dependency Injection.

    public class Computer {
      private Screen screen;
      
      public Computer(Screen screen) {
        this.screen = screen;
      }
    }

You can also make DI by the Setter-based method. This method is commonly used when you have optional dependencies that need to be injected. In mandatory dependencies, as a good practice, always use the constructor-based method.


# *SpringBoot*



**Spring Context:** Spring context or Spring IoC (Inversion of Control) containers, is responsible for configuring, instantiating and assembling beans by reading Java annotations, metadata from XML and Java code in the configuration files.

