**What is a Design Pattern?**
Before define what is a design pattern, you must to understand that this word can be used by other areas like architecture and engineer. For this reason, there are many diferent ways to define what is a design pattern, but all these variation, if you look at the core, have a resembling meaning. Given that, I'll try to give my own definition of what is a Design Pattern. Design Pattern is a known solution for a design problem that is very common in a specific context, and this solution can be applied to this problem many times as you wanted. Design Patterns besides of resolving a problem, they improve the documentation of a project. 

**How can we classificate them?**
We can classificate design patterns by two criteria. The first is **purpose**, and it reflets what a pattern does and inside this criteria, there are 3 subclassification, **creational, structural and behavioral**. The first purpose concerns about the object creation. The second one deals with composition of classes and objects. The last one characterize the ways in which classes and objects interact and distribute responsability. The second criteria is **scope** and specifies if the pattern can be applied in classes or in objects. Class patterns focus at the relationship between classes and their subclasses. On the other hand, the object patterns concern about object relationships, which can be more dynamic because they have status, and status can change at run time.

Creational class patterns delegate part of the object creation to subclasses. While Creational object patterns delegate it to another object. Structural class patterns use inheritance to compose classes, the Structural object patterns describe join objects. Behavioral class patterns use inheritance to describe algorithms and flow of control, whereas Behavioral object patterns describe how a group of objects cooperate to perform a task that only one object cannot do.

**Concepts**
Before diving into patterns, we must know some definions to improve our bases and consequently understand better the patterns.

**request** we can define a request as a message sent from a client objeto that makes another object performs an operation (call a method). Requests are the way that objects communicate, while  xugu operations are the only way to change an internal status from an object.

**Type** is a name used to denote a particular interface. We speak of an object as having the type "Window" if it accepts all operations defined in the interface named "Window". An object can have multiple types and different objects can have one same type.
