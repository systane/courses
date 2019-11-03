# **D - Dependency Inversion Principle (DIP)**

The last principle talks about Dependency Inversion and Uncle Bob says "Depend on abstractions, not on concretions". This concept is important because concretions may change, but abstractions is harder to change. Following this principle, and also the others, you can improve the level of cohesion in your project and turn it more flexible to changes in the future. Requirements and specifications can change when we build software and that's normal. Refactories must be done along the development of the project, but you need always to provide a flexible architecture to easier support these changes.

![PROBLEM_DIP](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/PROBLEM_DIP.png)

The above snippet shows us an example of violation of DIP, we have explicitly coupled two classes together, we observe it because we used the keyword `new`. To build a new instance of Windows98Machine, we MUST also create a StandardKeyboard. We cannot easily change our keyboard in run time and that's make our design rigid and not flexible (coupled). How can we solve this problem to enable the client of the Windows98Machine change keyboard? We can use a technique called **Dependency Injection (DI)** to solve this and consequently do not break the dependency inversion principle.

This snippet shows the refactoried classes, now we decoupled our two classes that before were tightly coupled.
![SOLUTION_DIP](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/SOLUTION_DIP.png)

You may remember that I also used DI (implicitly) when I was talking about [OCP Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/O_OpenClosedPrinciple.md).Let's remember the snippet where I applied this pattern:

![DI_EXAMPLE](https://github.com/systane/courses/blob/master/designPatterns/img/OCP/authenticateLogin_OCP.png)

Look at `@AllArgsConstructor` Lombok (A library to help us with boilerplate code) annotation, it creates a constructor method for you at compilation time and this constructor receives by parameter an instance of `Authentication` class like the above snippet:
![Dependency_Injection_Example](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/DI_EXAMPLE_DIP.png)

The above snippet shows us the main function with a little "problem". To use the AuthenticationLogin class the developer must manager by its own the construction of a new instance of AuthenticationGoogle. Imagine that you would have Class1 who depends on two other classes, Class2 and Class3. You'd probably have to instantiate two objects from the Class2 and Class3, and as much more dependencies your class has, harder will be to manager, and the things will start to get hairy. To solve this problem, some libraries and frameworks use a pattern called IoC - Inversion of Control. In this pattern instead of you creating new objects to achieve DI, you cann pass this responsability to framework, you invert the control of creating objects.

Let's look an example of how achieve DI with IoC.

![Example1_OIC](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/Example1_IoC.png)

In the above code, I created LoginService class and annotated it with `@Service`, this annotation tells to Spring that this class will be managed by the Spring Container, in other worlds I'm configuring Spring to make the IoC of this class. I also implemented a method that must validade the email and password from an incoming request made by a client.

![Example2_OIC](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/Example2_IoC.png)

In my controller class, I declared a private attribute and annotated it with `@Autowired`. This annotation tells to spring that this attribute must be created by IoC and injected in this class, then the developer don't need to manager all dependencies alone. 

As you can observe, DI and IoC are not DIP, Dependency Injection is just a pattern/technique that we can use to follow the DIP principle (Decouple our code), and IoC is a another pattern to help you achieve DI.

**OBS**: I'm not using DIP in the last example, because my controller class is depending directly on an implementation instead of abstraction.
