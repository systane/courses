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