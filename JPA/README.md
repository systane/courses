# **Java Persistence API (JPA)**

JPA is a Java application programming interface specification that enables you to manage and map relational tables to objects (ORM - Object Relational Mapping). Pay attention to the definition **interface specification**. Just with JPA you won't be able to use any implementation or functionality, you'll need to use a JPA implementation like for example Hibernate or EclipseLink.
The JPA is composed by three main areas:
- The JPA api;
- Java Persistence Query Language (JPQL);
- Object Relacional Mapping (ORM).

Initially, JPA was designed to specify rules for relational databases, but it evolved to support NoSQL databases too.

# **Hibernate**
Hibernate is the implementation of the set specifications definied in JPA.  It provides an ORM which map the domain model objects to the relational database tables and vice versa. Hibernate implements others functionalities like Validators to validate constraints, Queries, etc.


**Common performance problems**

With Hibernate you can map a relational table to a class, but is impossible to represent perfectly a relational table in the Object Oriented World. The **N+1 problem** is an example of this flaw. This problem happens in the following situation:

![n+1Problem](https://github.com/systane/courses/blob/master/JPA/img/n%2B1Problem.png)

You've mapped an Entity with `FetchType.LAZY`, and using this the Hibernate will only load the OrderItem entities when you access this attribute, using for example a getter method. In the most of the cases is recommended use the fetch lazy to avoid Hibernate bring the entire table records when you load an Order object.

But let's look a snippet code like the above:

![n+1Problem2](https://github.com/systane/courses/blob/master/JPA/img/n%2B1Problem2.png)

Here, we're accessing a List of Orders, and in each Order we retrieve the items of this Order. So looking in the SQL part of the thing, Hibernate will make a select for each Order and after retrieve this order from the database, it will make a select for each item of this order. In a ordem has N items, so N select will created and hit in the database, plus the first select to retrieve the Order. That's way the problem is known as N+1. The solution for this problem is simple if we think in a SQL Way, you just need to `join` the both tables and perform just 1 select, in other words you have to make sure that you load all the Items when you also load an Order. Hibernate provides a query language (HQL) to help us create easily OO queries using concepts like inheritance, association, etc.


# **Spring Data**

Spring Data is a part of the Spring Framework. It doesn't implement JPA specifications, in other words Spring Data isn't a JPA provider like Hibernate. The main goal of Spring Data is to reduce the amount of boilerplate code required to implement CRUD operatios and data access.