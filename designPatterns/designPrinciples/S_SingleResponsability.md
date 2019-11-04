# **S - Single Responsability Principle (SRP)**

This is the first principle and it says that "A class should have one, and only one, reason to change". In other words, Uncle Bob is talking about cohesion. Cohesion you can understand as how strong are the relationship between the elements of a class, if this relationship is strong, the elements must be related and have high affinity. A well defined class has a high cohesion.

**OBS**: The meaning of cohesion is different from cloupling. Clouping is when you have a strong dependency between elements, so you if change one, you are going to change the other element. 

![SRP_PROBLEM](https://github.com/systane/courses/blob/master/designPatterns/img/SRP/Problem_SRP.png)

The above snippet of code we have a class with low cohesion, what open connection with a database (DB) is related with Employee? These two responsabilities are so far away from each other. Another evidence that this class has a low cohesion is the 'calculateSalary' function. The concept of salary and employee are a little related, but if comes the necessity to calculate the salary from different positions based on taxes that this position must pay? We'd have to create another function?

The solution is apply the SRP. We can create a class to make the connection with DB, create an ENUM to represent different positions and a DAO to persist our Employee.

For example, our class to connect with DB can be like the above. What is the responsability of this class? Simple to answer: Just connect with Database. That's it.
![SRP_DB_CONNECTION](https://github.com/systane/courses/blob/master/designPatterns/img/SRP/dbConnection_SRP.png)

Our DAO class with just the responsability to save a new employee.
![SRP_DAO](https://github.com/systane/courses/blob/master/designPatterns/img/SRP/employeeDao_SRP.png)

Our ENUM and interface to calculate the salary
![SRP_ENUM](https://github.com/systane/courses/blob/master/designPatterns/img/SRP/Enum_SRP.png)

At the last our Employee entity, as you can see its becomes clearer than before:
![SRP_EMPLOYEE](https://github.com/systane/courses/blob/master/designPatterns/img/SRP/employeeEntity_SRP.png)

With this separations of responsabilities each class has only one reason to change, and all elements/components used within each class have a high cohesion with the other elements in the same class.
