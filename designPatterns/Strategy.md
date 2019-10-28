# **Strategy** 
This pattern encapsulate every algorithm from a set of different algorithms and make them interchangeable. This patterns allow the client change algorithms easily at runtime. This logic behind this pattern is split the set of algorithm implementations from the context that they are required. You can have your Context class separated from the Strategy class that will hold all implementation details from those algorithms.

This pattern has 3 main participants:
- **Strategy**: The first is the Strategy (Compositor) class. This interface must be common to all algorithm concrete classes (ConcreteStrategy), and it will through this interface that Context (Composition) class would access the different algorithm implements.
- **ConcreteStrategy**: Represent all concrete classes that assign the Strategy class. In these classes go all the specific implementations about the algorithms.
- **Context**: The last participant is the Context (Composition) class. This class in responsible to forward all the requests from the client to the Strategy class. The client often decide to change the algorithm and the context class is responsible to configure the right ConcreteStrategy. This class can also mantain an interface, so then the Strategy class access its data.  

![strategyStructure](https://github.com/systane/courses/blob/master/designPatterns/img/strategyStructure.png)

This pattern can be used many cases, like for example when you hide a complex algorithm implementation from client. You can also apply this pattern in a bunch of multiple conditional statements. Instead of multiple conditional, you move the conditional related code to a ConcreteStrategy class. Another case that you can apply this pattern is when you have different algorithms variants and you need to trade off between them easily. Strategy can be used when these algorithms are implemented as class hierarchy of algorithms in other words, you can have the same behavior with different implementations.

A drawback from this pattern is that the client must know how the algorithms differ from each other before the client decide to select one.