# **Bridge**
This pattern decouple an interface from its implementations. The main purpose to do this, is that these two classes can vary independently. When you create an interface an use it as a way to inheritance and create subclasses you bind all the operations in the interface with the implementations from these operations in the concrete classes. But if you need to modify, extend and reuse abstractions and implementations independently? This pattern comes in hand to help you make this decouples real.

![bridgeEstructure](https://github.com/systane/courses/blob/master/designPatterns/img/bridgeStructure.png)

The above figure showns the structure and the relatioship between the classes from the Bridge.The pattern split the abstraction and the implementation in separate class hierarchies. The Abstraction and Implementor classes make a "Brigde". The scheme of this pattern is composed basically of 4 members:
- **Abstraction**: This class maintain a reference to an object of type Implementor and it also defines the interface of the abstraction.
- **RefinedAbstraction**: Are classes that implement or extend the Abstraction class.
- **Implementor**: This class defines the interface for implementation classes. This class doesn't have to match exactly with the Abstraction's interface. Generally, this class defines primitive operations and the Abstraction provides higher-level operations based on these primitives.
- **ConcreteImplementor**: These classes assign the Implementor's interface and implement all operations declared.

