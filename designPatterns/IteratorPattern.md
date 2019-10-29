# **Iterator**

This pattern provides a way to access elements of an aggregate object (An Array, List, etc) sequentially without exposing its internal structure. With this pattern we can also traverse this aggregate object in different ways.

The key is to encapsulate the responsability to access and traverse the aggregate object with an Iterator class that defines an interface for accessing the list of elements.  This pattern keeps tracking the current element from this list in other words, the iterator object knows which elements have been traversed already.

