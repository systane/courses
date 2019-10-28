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