# **Spring Framework: An Overview**
Spring is a modular framework to web development, and it has many features that we'll be talking in this article. But first let's define the base concepts to understand how this awesome framewok works.

**Ioc Containers:** The Spring container is the core of the Spring Framework. Containers will create, configure, wire and manage objects and their entire life cycle. With the DI (Dependency Inversion) spring can manage this objects (Spring Beans) easily.

The container gets its instructions of what Beans to instantiate, configure and assemble by reading the configuration metadata provided. This configuration can be Java annotation, XML or Java code.

There are two types of Spring containers:

- *Spring BeanFactory Container:* 
  Is the simplest container in the Spring Framework and it still present just for the sake of backward compatibility with other frameworks that integrate with Spring.

- *Spring ApplicationContext Container:* 
  This is the most modern Spring container with ability to resolve textual messages from a properties file and other functionalities specialy the Spring BeanFactory stuff.

**Beans:** they are the objects that form the backbone of your application. They're managed by the Spring Ioc Container (Spring Context). 

Beans have some important properties like the **scope**. This attribute defines how Spring Context will instance a new bean each time one is needed. If you need only one instance of a bean, you should define the scope as **singleton** (default scope), otherwise if you need a new instance on each request you should use a **request** scope. In addition to that, you can also configure methods to execute before the destruction or after the initialization of a bean or a group of beans. You can also entirely customize the instantiation logic, dependency-resolution logic of one bean, just defining BeanPostProcessor implementations.

**Dependency Injection:** It Helps you in gluing classes together and the same time keeping them independent and the entire procedure of DI can be controlled by the Spring Framework just using annotations. 

But what is Dependency Injection in the pratice?
In the bellow code the Computer class knows how the Screen class is build and all its dependencies (if the screen would had depended of other classes, the computer class will be know about it).

    public class Computer {
      private Screen screen;
      
      public Computer() {
        screen = new Screen();
      }
    }


But let's take a look in another piece of code, the Computer class knows nothing about the Screen, and that's Dependency Injection.

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

As shown in the above diagram the DispatcherServlet receives all the HTTP request and consults the HandlerMapping to call the appropriate Controller. After the Controller takes the request and make all the necessary processing, it will returns the view name to the DispatcherServlet. The DispatcherServlet will take help from ViewResolver to pick up the defined view to return in the response. Once the view is ready, the DispatcherServlet passes the necessary data to the view wich is finally rendered on the browser.

# *Spring Boot*

Spring Boot is a framework used to create a Micro Service, it and enables you to develop application that you can just run. You can start a spring boot application with the minimum configuration.

**How does it work?**
Spring Boot automatically configures your application based on the dependencies you have added to the project by usind **@EnableAutoConfiguration**. For example, if you have MySQL database in your classpath, but you haven't configured any database connection, then Spring Boot auto-configures an in-memory database.

The start point of any Spring Boot application is the class with **@SpringBootApplication**. The framewok will automatically scan all the component included in the project by usind **@ComponentScan**. The **@SpringBootApplication** includes **@EnableAutoConfiguration** (Enable the auto configuration of the spring), **@ComponentScan** and **@SpringBootConfiguration**.


# *Spring Batch*

Spring Batch is a open source framework designed to execute jobs (batch processing). Spring Batch provides many features that help you in the development of jobs like classes and API to read/write resources, job processing statistics, transaction management, and so on.

## **Domain Language**

The below image shows the basic architecture of batch processing and Spring has the same concepts. As we can see, a Job has one or more Steps, which has one ItemReader, ItemProcessor and maybe one Writer. The lastone isn't required for each step.

![Batch Architecture](https://docs.spring.io/spring-batch/trunk/reference/html/images/spring-batch-reference-model.png)



-  **Job:** Is an entity that encapsulates an entire batch process. A job will be wired with an XML configuration file or Java based configuration. In Spring Batch, a Job is a container for Steps. It combines the steps in a flow and allows for configuration of properties global to all steps (the name of the job, definition and ordering of steps, etc).

- **JobInstance:** Refers to the concept of a logical job run. A job that should execute each day has many one instance per day. At 1st January it will exist one job instance, at 2nd January another job instance. However, each JobInstance can have multiple JobExecution and one JobInstance corresponding to a particular Job and identifying JobParameters can be running at a given time. Using the ExecutionContext of the JobInstance of 1st January in the JobInstance of 2nd January will means 'start from wher you left off', otherwise using a new JobInstance means 'start from beginning'.

- **JobParameters:** They are used to distinguish one JobInstance from another. Basically JobParamets are a set of parameters used to start a batch job.

- **JobExecution:** Refers to the technical concept of a single attempt to run a Job. It is the primary storage mechanism for what actually happened during a run.

- **Step:** Is a domain object that encapuslates an independent, sequential phase of a batch job. Each step has an individual StepExecution.

- **JobLauncher:** Represents a simple interface for launching a Job with a given set of JobParameters.

- **Listerners:** Listerners can be used to listen events that may happen in a job execution.For example, if an ItemWriter needs to be notified when the Step has been completed, to start writing itens at database, you can configure an listener.


# *Spring Cloud*

Spring Cloud is a set of tool (or libraries) for quick development of distributed systems following up common patterns like service discovery, circuit breakers and etc. Inside Spring Cloud is possible to find many projects like:

- **Spring Cloud Netflix:** It's a project that integrates various Netflix OSS components (Zuul, Eureka, Hystrix, etc.).

- **Spring Cloud Config:** A project to centralize configuration management backed by a git repository in a network of microservice applications.

- **Spring Cloud AWS:** It offers a convenient way to interact with AWS. You can develop your application without having to care about infrastructure or maintenance.

And this list goes on...

## **Spring Cloud Netflix**

Spring Cloud Netflix provides Netflix OSS integrations for Spring Boot app through autoconfiguration and binding to the Spring Environment. It helps you easily configure and apply common patterns in your distributed systems with Netflix components. The patterns included in this pack are Service Discovery with **Eureka**, Circuit Breaker provided by **Hystrix**, the Intelligent Routing system of **Zuul** and Client Side Load Balancing  with **Ribbon**.

Talking a little more about these provided services:

- **Eureka:** Eureka provides an implementation of service discovery pattern. This pattern is an important pattern commonly utilized in microservice-based architecture. How can we identify a service in a network with 20 or more hosts running? How can we know that if this particular service is online? If so, which IP and Port? If this IP address changes dynamically in the next seconds/minutes/hours? 

  ![Eureka](https://docs.pivotal.io/spring-cloud-services/1-5/common/service-registry/images/Netflix-Eureka-d1.png)

  Eureka comes to solve this problem. With Eureka, each service that we put online can be configured to make a self-register in the Eureka Server. This register includes providing the hostname, port, health check, etc. After that, Eureka Server receives messages of heartbeat of each service ou instance that is running, so Eureka Service can determine which instance of which service is running in a given time. Each service registered also fetch connection and status information about other registered applications.
  

- **Hystrix:** Hystrix is a library that implements the circuit breaker pattern. By default, this library encapsulates and monitors all the requests. Look at the image below and imagine that the payment service is offline because some fault that has occurred. After this fault, the payment service is starting again responding requests with low capacity meanwhile the driver management is sending tons of requests to payment service. In this scenario, the payment service will fall again. 

  Hystrix provides efficient mechanisms of resilience. Hystrix implements the circuit breaker that with some metrics can give a change to payment service recovery from faults and prevent this kind of problem to occur. You can wrap your asynchronous requests and in case of failure give predefined responses. The algorithm can also open the circuit stoping the fallen server of receiving requests until it recovers.

  ![](https://i.pinimg.com/originals/09/6e/3c/096e3cabb876856243faf4b5b201073d.png)


- **Zuul:** Is the Gatekeeper of Gozer and got famous after appearing in the Ghostbusters movie of 1984.
  ![](https://aa1a5178aef33568e9c4-a77ea51e8d8892c1eb8348eb6b3663f6.ssl.cf5.rackcdn.com/p/full/3c8c7df1-8d25-48b5-904d-9c055f790c0e.jpg)

   All kidding aside, Zuul is a subproject of Spring Cloud Netflix that is commonly used as gateway in a microservice-based architecture. So it really takes after with the Zuul from Ghostbusters. As an edge service, Zuul is built to enable dynamic routing, monitoring, resiliency, and security. Zuul can also provide routing requests across AWS regions in order to diversify the Load Balance usage.

  ![](https://exampledriven.files.wordpress.com/2016/07/zuul-api-gateway.jpg)


- **Ribbon:** Ribbon is a library inside of Zuul that is responsible to make all load balance of the requests. By default, it's configured to use a simple Round Robin balancing between the online instances of each service. The load balancing done here is a client load balance, is load balance via software, which means that there is no host handling with the request and redirecting to the correct host. In client load balance each instance of each service has a list with the address and status of other services, so that, the client can redirect the request to the correct host. As said, the default algorithm utilized is the Round Robin but can be easily changed based on the health check of each instance, or other standards.