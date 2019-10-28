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