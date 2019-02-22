# **Spring Framework: An Overview**
Spring is a modular framework to web development,and it has many features that we'll be talking in this article. But first let's define the base concepts to understand how this awesome framewok works.

**Ioc Containers:** The Spring container is the core of the Spring Framework. Containers will create, configure, wire and manage objects their entire life cycle. With the DI (Dependency Inversion) spring can manage this objects (Spring Beans) easily.

The container gets its instructions of what Beans to instantiate, configure and assemble by reading the configuration metadata provided. This configuration can be Java annotation, XML or Java code.

There are two of types of Spring containers:

- *Spring BeanFactory Container:* 
  Is the simplest container in the Spring Framework and it still present just for the sake of backward compatibility with other frameworks that integrate with Spring.

- *Spring ApplicationContext Container:* 
  This is the most modern Spring container with ability to resolve textual messages from a properties file and other functionalities specialy the Spring BeanFactory stuff.

**Beans:** they are the objects that form the backbone of your application. They're managed by the Spring Ioc Container (Spring Context). 

Beans have some important properties like the **scope**. This attribute defines how Spring Context will instance a new bean each time one is needed. If you need only one instance of a bean, you should define the scope as **singleton** (default scope), you need a new instance on each request you should use a **request** scope. in addition to that, you can also configure methods to execute before the destruction or after the initialization of a bean or a group of beans. If all this functionalities doens't fit, you can entirely customize the instantiation logic, dependency-resolution logic just defining BeanPostProcessor implementations.

**Dependency Injection:** Helps you in gluing classes together and the same time keeping them independent and the entire procedure of DI is controlled by the Spring Framework. In the bellow code the Computer class knows how the Screen class is build and all its dependencies (if the screen would had depended of other classes, the computer class will be know about it).

    public class Computer {
      private Screen screen;
      
      public Computer() {
        screen = new Screen();
      }
    }


But in the bellow piece of code, the Computer class knows nothing about the Screen, and that's Dependency Injection.

    public class Computer {
      private Screen screen;
      
      public Computer(Screen screen) {
        this.screen = screen;
      }
    }

You can also make DI by the Setter-based method. This method is commonly used when you have optional dependencies that need to be injected. In mandatory dependencies, as a good practice, always use the constructor-based method.

**Configuring:** You can configure Spring using either XML or Java-based configuration. The last one is the most common way to configure Spring. To help us with this work the Spring defines some annotations: **@Configuration**, **@Bean** and **@Import**, which the first tells to Spring IoC Container that every class with this annotation can be used as a source of bean definitions. The Second annotation is more used in methods, and it tells to Spring Ioc Container that the object returned from method should be registered as a bean in the Spring Context. The last one annotation is used to load Beans from another configuration class.

# *Spring - JDBC Framework*
Spring JDBC Framework takes care of all the low-level details starting from opening/closing a connection, execute the SQL statement, process exception, etc. You just need to define the connection parameters and the SQL statement to be executed. This configuration can be done by defining an DataSource Bean and this dataSource will be shared into your DAO classes.

# *Spring - MVC Framework*

Spring mvc framework provides an MVC architecture that easy and flexible the web development with ready components. The following diagram shows how Spring deals with the http request/responses.

![Architecture Spring MVC](https://www.tutorialspoint.com/spring/images/spring_dispatcherservlet.png)

As shown in the aboce diagram the DispatcherServlet receives all the HTTP request and consults the HandlerMapping to call the appropriate Controller. After the Controller takes the request and make all the necessary processing, it will returns the view name to the DispatcherServlet. The DispatcherServlet will take help from ViewResolver to pick up the defined view to return in the response. Once the view is ready, the DispatcherServlet passes the necessary data to the view wich is finally rendered on the browser.

# *Spring Boot*

Spring Boot is a framework used to create a Micro Service, it and enables you to develop application that you can just run. You can start a spring boot application with the minimum configuration.

**How does it work?**
Spring Boot automatically configures your application based on the dependencies you have added to the project by usind **@EnableAutoConfiguration**. For example, if you have MySQL database in your classpath, but you haven't configured any database connection, then Spring Boot auto-configures an in-memory database.

The start point of any Spring Boot application is the class with **@SpringBootApplication**. The framewok will automatically scan all the component included in the project by usind **@ComponentScan**. The **@SpringBootApplication** includes **@EnableAutoConfiguration** (Enable the auto configuration of the spring), **@ComponentScan** and **@SpringBootConfiguration**



