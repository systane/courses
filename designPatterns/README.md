# **What is a Design Pattern?**
Before define what is a design pattern, you must to understand that this word can be used by other areas like architecture and engineer. For this reason, there are many diferent ways to define what is a design pattern, but all these variation, if you look at the core, have a resembling meaning. Given that, I'll try to give my own definition of what is a Design Pattern. Design Pattern is a known solution for a design problem that is very common in a specific context, and this solution can be applied to this problem many times as you wanted. Design Patterns besides of resolving a problem, they improve the documentation of a project. 

# **How can we classificate them?**
We can classificate design patterns by two criteria. The first is **purpose**, and it reflets what a pattern does and inside this criteria, there are 3 subclassification, **creational, structural and behavioral**. The first purpose concerns about the object creation. The second one deals with composition of classes and objects. The last one characterize the ways in which classes and objects interact and distribute responsability. The second criteria is **scope** and specifies if the pattern can be applied in classes or in objects. Class patterns focus at the relationship between classes and their subclasses. On the other hand, the object patterns concern about object relationships, which can be more dynamic because they have status, and status can change at run time.

Creational class patterns delegate part of the object creation to subclasses. While Creational object patterns delegate it to another object. Structural class patterns use inheritance to compose classes, the Structural object patterns describe join objects. Behavioral class patterns use inheritance to describe algorithms and flow of control, whereas Behavioral object patterns describe how a group of objects cooperate to perform a task that only one object cannot do.

# **Why I must know Design Patterns? Arey they used in nowadays projects?**
Design patterns are used in many projects nowadays. I think that is almost impossible don't see at the least one of the patterns described here in an expressive project. You might use a Facade to organize better your services ou to isolate some complex layer of your project. You might use a

# **Concepts**
Before diving into patterns, we must know some definions to improve our bases and consequently understand better the patterns.

**request** we can define a request as a message sent from a client objeto that makes another object performs an operation (call a method). Requests are the way that objects communicate, while operations are the only way to change an internal status from an object.

**Type** is a name used to denote a particular interface. We speak of an object as having the type "Window" if it accepts all operations defined in the interface named "Window". An object can have multiple types and different objects can have one same type.

**Delegation** is the ability to delegate an operation to the its delegate. For example instead of making class Window a subclass of Rectangle (because windows used to be a rectangular shape), the windows class might reuse the behavior of Rectangle by keeping a Rectangule instance variable and delegating any specific rectangule operation to it. Window can forward requests to its Rectangule instance, instead of inheriting those operations.

In the bellow example, we need to calculate the area of a Window, so then we created an Rectangule instance and delegate to it this particular operation. If we have choose to extends Windows classe as Rectangle, our implementation would be more static and if the Window class becomes circular, it would be harder to change it at the run time.

![delegationExample](https://github.com/systane/courses/blob/master/designPatterns/img/delegation.png)

This implementation give flexibility, because we can easily change our Window to circular instead of rectangular just changing the instance to Circular whereas Rectangular. The trade off is our implementation also becomes more dynamic and so then harder to understand than static software. Delegation is a good design to choose when it simplifies more than it complicates.

Delegation shows us the concept of **composition**, we can compose a classe with instance from another. Moreover, delegation also shows that we can always replace inheritance with composition to reuse code.


# **Design Patterns**

Each pattern described bellow has an implementation in the project folder. I Added some commentaries to explain the problem which this pattern can solve, and I also named all the classes with the correspondenting name convention used in the diagrams to describe the strucuture of these patterns. 

All of these patterns described are listed in the book [Design Pattern, Elements of Reusable Object-Oriented Software](https://www.amazon.com.br/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612).

- [CompositePattern](https://github.com/systane/courses/blob/master/designPatterns/CompositePattern.md)

- [StrategyPattern](https://github.com/systane/courses/blob/master/designPatterns/StrategyPattern.md)

- [DecoratorPattern](https://github.com/systane/courses/blob/master/designPatterns/DecoratorPattern.md)

- [AbstractFactoryPattern](https://github.com/systane/courses/blob/master/designPatterns/AbstractFactoryPattern.md)

- [BridgePattern](https://github.com/systane/courses/blob/master/designPatterns/BridgePattern.md)

- [CommandPattern](https://github.com/systane/courses/blob/master/designPatterns/CommandPattern.md)

- [IteratorPattern](https://github.com/systane/courses/blob/master/designPatterns/IteratorPattern.md)

# **Design Principles**
When you talk about OO there are a bunch of acronyms that appear trying to guide you to use the best design principles, if you have never heard about KISS, DRY, YAGNI, etc. You may think that I'm talking about a soup of letters, but unfortunately these are well known design principles. Wait, why unfortunately? Because all them in a certain way represents parts of well known principle, SOLID. I think that knowing SOLID and applying it also follow another principles by default. Look, I'm not trying to say that you just need to know SOLID, it is OK read about other principles, because all they together converge to the object: Improve your code legibility and maintenance. I just think that our community have been creating so many acronyms that could be sometimes explained with SOLID.

Differences apart, let's focus on what is important, what the fuck is SOLID? SOLID is an acronym created by Robert C. Martin (Uncle Bob) and it means:

**[S]**[ingle Responsibility Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/S_SingleResponsability.md)

**[O]**[pen/Closed Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/O_OpenClosedPrinciple.md)

**[L]**[iskov Substitution Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/L_LiskovSubstitutionPrinciple.md)

**[I]**[nterface Segregation Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/I_InterfaceSegregationPrinciple.md)

**[D]**[ependency Inversion Principle](https://github.com/systane/courses/blob/master/designPatterns/designPrinciples/D_DependencyInversionPrinciple.md)

These principles are very abstract, so is difficult imagine that they only are applied in classes and functions (code). We can also apply the SOLID principle in a bigger context like an app or features from a system.