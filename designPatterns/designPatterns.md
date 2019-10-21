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

**Composite** Is a pattern that compose objects into tree strucuture to represent part-whole hierarchies (GoF - Design Patterns: Elements of Reusable Object-Oriented Software, 1994). The client can treat individual objects and compositions of objects in the same way.

We can break this pattern into:
- **component** This is the base interface (can also be an abstract class) that will hold the abstract methods that are common to all child components.
- **leaf** This class implements the behavior of the base components and doesn't have any reference to the other objects.
- **composite**  This class contains the leaf elements, and it also implements the base component methods and define child-related operations.
- **client** Can manipulate objects in the composition through the Component interface.

![compositeStructure](https://github.com/systane/courses/blob/master/designPatterns/compositeStructure.png)

This pattern is used in situations when you want clients treat all objects in the same way. This objects can be a composition of objects or individual objects.

Following the structure shown in the last picture, we have the Component that might be an abstract class or an interface. This class must be implemented by the Leaf and Composite classes. Leaf will only define the specific methods for an individual object. Composite class implements both Leaf and Composite methods.This class also has an aggregation relationship with Component to allow an composite object create a tree children like the one shown in the bellow picture.

![compositeTree](https://github.com/systane/courses/blob/master/designPatterns/compositeTree.png)