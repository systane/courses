# **Iterator**

This pattern provides a way to access elements of an aggregate object (An Array, List, etc) sequentially without exposing its internal structure. With this pattern we can also traverse this aggregate object in different ways.

The key is to encapsulate the responsability to access and traverse the aggregate object with an Iterator class that defines an interface for accessing the list of elements.  This pattern keeps tracking the current element from this list in other words, the iterator object knows which elements have been traversed already.

![iteratorStructure](https://github.com/systane/courses/blob/master/designPatterns/img/IteratorStructure.png)

The structure shown is composed of 4 elements:
- **Iterator**: This clas defines an interface for accessing and traversing elements.
- **ConcreteIterator**: Concrete class from Iterator, it keeps track of current position in the traversal.
- **Aggregate**: This class defines an interface for creating an Iterator object.
- **ConcreteAggregate**: This concrete clas implement the abstract method from Aggregate interface.


