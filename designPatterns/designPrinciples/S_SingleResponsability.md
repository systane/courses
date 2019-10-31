# **S - Single Responsability Principle (SRP)**

This is the first principle and it says that "A class should have one, and only one, reason to change". In other words, Uncle Bob is talking about cohesion. Cohesion you can understand as how strong are the relationship between the elements of a class, if this relationship is strong, the elements must be related and have high affinity. A well defined class has a high cohesion.

OBS: The meaning of cohesion is different from cloupling. Clouping is when you have a strong dependency between elements, so you change one, you are going to change the other element. 

![SRP_PROBLEM](https://github.com/systane/courses/blob/master/designPatterns/img/SRP_Problem.png)

The above snippet of code we have a class with low cohesion, what open connection with a database (DB) is related with Employee? These two responsabilities are so far away from each other. Another evidence that this class has a low cohesion is the 'calculaSalario' function that must calculate the salary from an employee. These two concepts are a little related, but if comes the necessity to calculate the salary from different positions based on taxes that this position must pay? We'd to create another function?

The solution is apply the SRP. We can create a class to make the connection with DB, create an ENUM to represent different positions and a DAO to persist our Employee.