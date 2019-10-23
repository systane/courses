**What is a Design Pattern?**
Before define what is a design pattern, you must to understand that this word can be used by other areas like architecture and engineer. For this reason, there are many diferent ways to define what is a design pattern, but all these variation, if you look at the core, have a resembling meaning. Given that, I'll try to give my own definition of what is a Design Pattern. Design Pattern is a known solution for a design problem that is very common in a specific context, and this solution can be applied to this problem many times as you wanted. Design Patterns besides of resolving a problem, they improve the documentation of a project. 

**How can we classificate them?**
We can classificate design patterns by two criteria. The first is **purpose**, and it reflets what a pattern does and inside this criteria, there are 3 subclassification, **creational, structural and behavioral**. The first purpose concerns about the object creation. The second one deals with composition of classes and objects. The last one characterize the ways in which classes and objects interact and distribute responsability. The second criteria is **scope** and specifies if the pattern can be applied in classes or in objects. Class patterns focus at the relationship between classes and their subclasses. On the other hand, the object patterns concern about object relationships, which can be more dynamic because they have status, and status can change at run time.

Creational class patterns delegate part of the object creation to subclasses. While Creational object patterns delegate it to another object. Structural class patterns use inheritance to compose classes, the Structural object patterns describe join objects. Behavioral class patterns use inheritance to describe algorithms and flow of control, whereas Behavioral object patterns describe how a group of objects cooperate to perform a task that only one object cannot do.

**Concepts**
Before diving into patterns, we must know some definions to improve our bases and consequently understand better the patterns.

**request** we can define a request as a message sent from a client objeto that makes another object performs an operation (call a method). Requests are the way that objects communicate, while operations are the only way to change an internal status from an object.

**Type** is a name used to denote a particular interface. We speak of an object as having the type "Window" if it accepts all operations defined in the interface named "Window". An object can have multiple types and different objects can have one same type.

**Delegation** is the ability to delegate an operation to the its delegate. For example instead of making class Window a subclass of Rectangle (because windows used to be a rectangular shape), the windows class might reuse the behavior of Rectangle by keeping a Rectangule instance variable and delegating any specific rectangule operation to it. Window can forward requests to its Rectangule instance, instead of inheriting those operations.

In the bellow example, we need to calculate the area of a Window, so then we created an Rectangule instance and delegate to it this particular operation. If we have choose to extends Windows classe as Rectangle, our implementation would be more static and if the Window class becomes circular, it would be harder to change it at the run time.

![delegationExample](https://github.com/systane/courses/blob/master/designPatterns/delegation.png)

This implementation give flexibility, because we can easily change our Window to circular instead of rectangular just changing the instance to Circular whereas Rectangular. The trade off is our implementation also becomes more dynamic and so then harder to understand than static software. Delegation is a good design to choose when it simplifies more than it complicates.

Delegation shows us the concept of **composition**, we can compose a classe with instance from another. Moreover, delegation also shows that we can always replace inheritance with composition to reuse code.


## **Patterns**

**Composite** is a pattern that compose objects into tree strucuture to represent part-whole hierarchies (GoF - Design Patterns: Elements of Reusable Object-Oriented Software, 1994). The client can treat individual objects and compositions of objects in the same way.

We can break this pattern into:
- **component** This is the base interface (can also be an abstract class) that will hold the abstract methods that are common to all child components.
- **leaf** This class implements the behavior of the base components and doesn't have any reference to the other objects.
- **composite**  This class contains the leaf elements, and it also implements the base component methods and define child-related operations.
- **client** Can manipulate objects in the composition through the Component interface.

![compositeStructure](https://github.com/systane/courses/blob/master/designPatterns/compositeStructure.png)

This pattern is used in situations when you want clients treat all objects (different objects) in the same way. This objects can be a composition of objects or individual objects and they form a tree hierarchical relationship.

Following the structure shown in the last picture, we have the Component that might be an abstract class or an interface. This class must be implemented by the Leaf and Composite classes. Leaf will only define the specific methods for an individual object. Composite class implements both Leaf and Composite methods.This class also has an aggregation relationship with Component to allow an composite object create a tree children like the one shown in the bellow picture.

![compositeTree](https://github.com/systane/courses/blob/master/designPatterns/compositeTree.png)

**trade-off** The composite pattern has an issue, you must choose where declare the child management operations (like add/remove methods). Declare the child menagement methods at the root of the class hierarchy gives you transparency, because the client can treat every object (composite or leaf) in a uniformly way. But there is a risky of a client call these methods from leaves. Another option is defining the child management operations in the Composite class, this gives you safety (you'll only call methods from composite object), but you lose transparency, because leaf and composite objects have different interfaces.

One approach to overcomes the problem of safety when choosing the transparency implementation, is implementing a default method in the abstract class (Component Class) to return `null` and in the Composite class overrides this method to return itself. But doing this, you are not treating all objects uniformly.

http://www.codinghelmet.com/articles/reduce-cyclomatic-complexity-composite-design-pattern


**Strategy** this pattern encapsulate every algorithm from a set of different algorithms and make them interchangeable. This patterns allow the client change algorithms easily. This logic behind this pattern is split the set of algorithm implementations from the context that they are required. You can have your Context class separated from the Strategy class that will hold all implementation details from those algorithms.

This pattern has 3 main participants:
    - Strategy: The first is the Strategy (Compositor) class. This interface must be common to all algorithm concrete classes (ConcreteStrategy), and it will through this interface that Context (Composition) class would access the different algorithm implements.
    - ConcreteStrategy: Represent all concrete classes that assign the Strategy class. In these classes go all the specific implementations about the algorithms.
    - Context: The last participant is the Context (Composition) class. This class in responsible to forward all the requests from the client to the Strategy class. The client often decide to change the algorithm and the context class is responsible to configure the right ConcreteStrategy. This class can also mantain an interface, so then the Strategy class access its data.  

![strategyStructure](https://github.com/systane/courses/blob/master/designPatterns/strategyStructure.png)

This pattern can be used many cases, like for example when you hide a complex algorithm implementation from client. You can also apply this pattern in a bunch of multiple conditional statements. Instead of multiple conditional, you move the conditional related code to a ConcreteStrategy class. Another case that you can apply this pattern is when you have different algorithms variants and you need to trade off between them easily. Strategy can be used when these algorithms are implemented as class hierarchy of algorithms in other words, you can have the same behavior with different implementations.

A drawback from this pattern is that the client must know how the different algorithms differ from each other before the client decide to select one.