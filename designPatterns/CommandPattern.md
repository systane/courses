# **Command**
This pattern is used when there is the necessity to encapsulate a request as an object without know anything about this operation. This command object can be stored and passed as parameter like any other object. The key for this pattern is the Command class (An interface) that defines an abstract method which will be implemented for all the Concrete Command subclasses. Concrete Command classes have an instance to the Receive class and is this class that has the required knowledge to carry out the request.

This figure shows how this pattern is organized:

![commandStructure](https://github.com/systane/courses/blob/master/designPatterns/img/commandStructure.png)

- **Command**: An interface for executing an operation
- **ConcreteCommand**: This concrete subclasses workds like a binding between Command and Receiver classes, because they implement the abstract method from Command class and also invoke corresponding operation(s) on Receiver. Of course this class must have a reference variable for the Receiver class.
- **Client**:  Creates a ConcreteCommand object and set its receiver.
- **Invoker**: This class has a reference variable that allows pass toward the request to the correspondingly ConcreteCommand class inderectly, because this class don't know wich subclasses will carry out the request.
- **Receiver**: Any class may works like a Receiver. The only restriction is that this class must have the knowledge to carry out the request associated with the operation performed.

This pattern decouples the object that invokes the operation from the one that knows how to perform it.