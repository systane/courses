# **D - Dependency Inversion Principle (DIP)**

The last principle talks about Dependency Inversion and Uncle Bob says "Depend on abstractions, not on concretions". This concept is important because concretions may change, but abstractions is harder to change. Following this principle, and also the others, you can improve the level of cohesion in your project and turn it more flexible to changes in the future. Requirements and specifications can change when we build software and that's normal. Refactories must be done along the development of the project, but you need always to provide a flexible architecture to easier support these changes.

![PROBLEM_DIP](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/PROBLEM_DIP.png)

The above snippet shows us an example of violation of DIP, we have explicitly coupled two classes together, we observe it because we used the keyword `new`. To build a new instance of Windows98Machine, we MUST also create a StandardKeyboard. We cannot easily change our keyboard in run time and that's make our design rigid and not flexible. How can we solve this problem to enable the client of the Windows98Machine change keyboard? We can use a technique called **Dependency Injection (DI)** to solve this and consequently do not break the dependency inversion principle.

This snippet shows the refactoried classes, now we decoupled our two classes that before were tightly coupled.
![SOLUTION_DIP](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/SOLUTION_DIP.png)

You may remember that I also used DI (implicitly) when I was talking about [OCP Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/O_OpenClosedPrinciple.md).Last remember the snippet where I applied this pattern:

![DI_EXAMPLE](https://github.com/systane/courses/blob/master/designPatterns/img/OCP/authenticateLogin_OCP.png)

Look at `@AllArgsConstructor` Lombok annotation, it creates a constructor method for you at compilation time and this constructor receives by parameter an instance of `Authentication` class like the above snippet:
![Dependency_Injection_Example](https://github.com/systane/courses/blob/master/designPatterns/img/DIP/DI_EXAMPLE_DIP.png)

As you can observe, DI are not DIP, Dependency Injection is just a pattern/technique that we can use to follow the DIP principle.

IOC