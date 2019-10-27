# **What is a Design Pattern?**
Before define what is a design pattern, you must to understand that this word can be used by other areas like architecture and engineer. For this reason, there are many diferent ways to define what is a design pattern, but all these variation, if you look at the core, have a resembling meaning. Given that, I'll try to give my own definition of what is a Design Pattern. Design Pattern is a known solution for a design problem that is very common in a specific context, and this solution can be applied to this problem many times as you wanted. Design Patterns besides of resolving a problem, they improve the documentation of a project. 

# **How can we classificate them?**
We can classificate design patterns by two criteria. The first is **purpose**, and it reflets what a pattern does and inside this criteria, there are 3 subclassification, **creational, structural and behavioral**. The first purpose concerns about the object creation. The second one deals with composition of classes and objects. The last one characterize the ways in which classes and objects interact and distribute responsability. The second criteria is **scope** and specifies if the pattern can be applied in classes or in objects. Class patterns focus at the relationship between classes and their subclasses. On the other hand, the object patterns concern about object relationships, which can be more dynamic because they have status, and status can change at run time.

Creational class patterns delegate part of the object creation to subclasses. While Creational object patterns delegate it to another object. Structural class patterns use inheritance to compose classes, the Structural object patterns describe join objects. Behavioral class patterns use inheritance to describe algorithms and flow of control, whereas Behavioral object patterns describe how a group of objects cooperate to perform a task that only one object cannot do.

# **Concepts**
Before diving into patterns, we must know some definions to improve our bases and consequently understand better the patterns.

**request** we can define a request as a message sent from a client objeto that makes another object performs an operation (call a method). Requests are the way that objects communicate, while operations are the only way to change an internal status from an object.

**Type** is a name used to denote a particular interface. We speak of an object as having the type "Window" if it accepts all operations defined in the interface named "Window". An object can have multiple types and different objects can have one same type.

**Delegation** is the ability to delegate an operation to the its delegate. For example instead of making class Window a subclass of Rectangle (because windows used to be a rectangular shape), the windows class might reuse the behavior of Rectangle by keeping a Rectangule instance variable and delegating any specific rectangule operation to it. Window can forward requests to its Rectangule instance, instead of inheriting those operations.

In the bellow example, we need to calculate the area of a Window, so then we created an Rectangule instance and delegate to it this particular operation. If we have choose to extends Windows classe as Rectangle, our implementation would be more static and if the Window class becomes circular, it would be harder to change it at the run time.

![delegationExample](https://github.com/systane/courses/blob/master/designPatterns/img/delegation.png)

This implementation give flexibility, because we can easily change our Window to circular instead of rectangular just changing the instance to Circular whereas Rectangular. The trade off is our implementation also becomes more dynamic and so then harder to understand than static software. Delegation is a good design to choose when it simplifies more than it complicates.

Delegation shows us the concept of **composition**, we can compose a classe with instance from another. Moreover, delegation also shows that we can always replace inheritance with composition to reuse code.


# **Patterns**

# **Composite**
 Is a pattern that compose objects into tree strucuture to represent part-whole hierarchies (GoF - Design Patterns: Elements of Reusable Object-Oriented Software, 1994). The client can treat individual objects and compositions of objects in the same way.

We can break this pattern into:
- **component** This is the base interface (can also be an abstract class) that will hold the abstract methods that are common to all child components.
- **leaf** This class implements the behavior of the base components and doesn't have any reference to the other objects.
- **composite**  This class contains the leaf elements, and it also implements the base component methods and define child-related operations.
- **client** Can manipulate objects in the composition through the Component interface.

![compositeStructure](https://github.com/systane/courses/blob/master/designPatterns/img/compositeStructure.png)

This pattern is used in situations when you want clients treat all objects (different objects) in the same way. This objects can be a composition of objects or individual objects and they form a tree hierarchical relationship.

Following the structure shown in the last picture, we have the Component that might be an abstract class or an interface. This class must be implemented by the Leaf and Composite classes. Leaf will only define the specific methods for an individual object. Composite class implements both Leaf and Composite methods.This class also has an aggregation relationship with Component to allow an composite object create a tree children like the one shown in the bellow picture.

![compositeTree](https://github.com/systane/courses/blob/master/designPatterns/img/compositeTree.png)

**trade-off** The composite pattern has an issue, you must choose where declare the child management operations (like add/remove methods). Declare the child menagement methods at the root of the class hierarchy gives you transparency, because the client can treat every object (composite or leaf) in a uniformly way. But there is a risky of a client call these methods from leaves. Another option is defining the child management operations in the Composite class, this gives you safety (you'll only call methods from composite object), but you lose transparency, because leaf and composite objects have different interfaces.

One approach to overcomes the problem of safety when choosing the transparency implementation, is implementing a default method in the abstract class (Component Class) to return `null` and in the Composite class overrides this method to return itself. But doing this, you are not treating all objects uniformly.

http://www.codinghelmet.com/articles/reduce-cyclomatic-complexity-composite-design-pattern


# **Strategy** 
This pattern encapsulate every algorithm from a set of different algorithms and make them interchangeable. This patterns allow the client change algorithms easily at runtime. This logic behind this pattern is split the set of algorithm implementations from the context that they are required. You can have your Context class separated from the Strategy class that will hold all implementation details from those algorithms.

This pattern has 3 main participants:
- **Strategy**: The first is the Strategy (Compositor) class. This interface must be common to all algorithm concrete classes (ConcreteStrategy), and it will through this interface that Context (Composition) class would access the different algorithm implements.
- **ConcreteStrategy**: Represent all concrete classes that assign the Strategy class. In these classes go all the specific implementations about the algorithms.
- **Context**: The last participant is the Context (Composition) class. This class in responsible to forward all the requests from the client to the Strategy class. The client often decide to change the algorithm and the context class is responsible to configure the right ConcreteStrategy. This class can also mantain an interface, so then the Strategy class access its data.  

![strategyStructure](https://github.com/systane/courses/blob/master/designPatterns/img/strategyStructure.png)

This pattern can be used many cases, like for example when you hide a complex algorithm implementation from client. You can also apply this pattern in a bunch of multiple conditional statements. Instead of multiple conditional, you move the conditional related code to a ConcreteStrategy class. Another case that you can apply this pattern is when you have different algorithms variants and you need to trade off between them easily. Strategy can be used when these algorithms are implemented as class hierarchy of algorithms in other words, you can have the same behavior with different implementations.

A drawback from this pattern is that the client must know how the algorithms differ from each other before the client decide to select one.

# **Decorator** 
This pattern is useful when there is necessity to add new funcionalities to an object dynamically. Decorator is a flexible alternative to inheritances that creates a lot of specifics subclasses. This happens because the Decorator class can may or may not add responsabilities (enclose Decorator objects to the Component object) before or after forwarding the requests to the Component Object. This process to add/remove funcionalities is **transparency** because this pattern has a Decorator class that conforms to the Component's interface, so that, its presence is invisible to client. The bellow diagram shows the decorator structure.

![decoratorStructure](https://github.com/systane/courses/blob/master/designPatterns/img/DecoratorStructure.png)

In sume, the decorator has four participants:
- **Component**: This class define an interface for all the objects that will have responsabilities add to them dynamically.
- **ConcreteComponent**: Concrete class that defines an object which the responsabilities will be attached.
- **Decorator**: This abstract class maintains a reference to Component class and also defines an interface that conforms to Component's interface. 
- **ConcreteDecorator**: Concrete class that add responsabilities to the Component.

You can use this pattern to add responsabilities to individual objects dynamically and transparently, in other words, withou affecting other objects. You can also apply this pattern when extension by subclasses creates an explosion of subclasses to support every combination. Decorator classes lets you mix and match responsabilities for specifc Component class.

The decorator pattern change the skin of the objects instead of the gut. **If you have classes that are heavyweight is better use Strategy instead of Decorator** because it can cost to much to apply. This happens because the Decorator class most maintain an interface that conforms to the interface of the Component it decorates. To use decoratos is better keep a lightweight class (The definition class shouldn't focus on storing data.  The definition of the data representation should be deferred to subclasses).
Strategy pattern doesn't need to maintain a conformance interface, this pattern can define its own specialized interface and can be applied even if the Component is heavyweight.

Some objects from java.io API are examples of decorators, you can use a LineNumberInputStream to decorate a FileInputStream, for example.

# **Abstract Factory**

Abstract factory is design pattern that provides an interface for creating families of objects that are dependents ou related, without specifying their concrete classes. This pattern is commonly used when you have a family of objects that must be used together, and you need to enforce this constraint. Another case to apply the abstract factory is when a system should be independent of how its products are creaed, composed and represented.

![abstractFactoryStructure](https://github.com/systane/courses/blob/master/designPatterns/img/abstractFactoryStructure.png)

The above picture shows how is the structure of the Abstract Factory. It's is composed of 5 members:
- **AbstractFactory**: An interface class with methods to create abstract product objects.
- **ConcreteFactory**: This class is responsible to implement the methods to create product objects.
- **AbstractProduct**: Another interface for a type of product object.
- **ConcreteProduct**: This concrete class implements the AbstractProduct interface and define the methods to create the corresponding product object.
- **Client**: Only use the the AbstractFactory or AbstractProduct.

The main disavantage of this pattern is that supporting new products is difficult. If you need to create a new kind of product, you'll need to change the AbstractFactory class and all of its subclasses and this process can be painful. A workaround to this problem can be creating a parameter to operations that create objects. This flag com be an integer for example and it will identify the kind of product.

 **Bridge**
This pattern decouple an interface from its implementations. The main purpose to do this, is that these two classes can vary independently. When you create an interface an use it as a way to inheritance and create subclasses you bind all the operations in the interface with the implementations from these operations in the concrete classes. But if you need to modify, extend and reuse abstractions and implementations independently? This pattern comes in hand to help you make this decouples real.

![bridgeEstructure](https://github.com/systane/courses/blob/master/designPatterns/img/bridgeStructure.png)

The above figure showns the structure and the relatioship between the classes from the Bridge.The pattern split the abstraction and the implementation in separate class hierarchies. The Abstraction and Implementor classes make a "Brigde". The scheme of this pattern is composed basically of 4 members:
- **Abstraction**: This class maintain a reference to an object of type Implementor and it also defines the interface of the abstraction.
- **RefinedAbstraction**: Are classes that implement or extend the Abstraction class.
- **Implementor**: This class defines the interface for implementation classes. This class doesn't have to match exactly with the Abstraction's interface. Generally, this class defines primitive operations and the Abstraction provides higher-level operations based on these primitives.
- **ConcreteImplementor**: These classes assign the Implementor's interface and implement all operations declared.




