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

With Hibernate you can map a relational table to a class, but is impossible to represent perfectly a relational table in the Object Oriented World. The **N+1 problem** is an example of this flaw. 


# **Spring Data**

Spring Data is a part of the Spring Framework. It doesn't implement JPA specifications, in other words Spring Data isn't a JPA provider like Hibernate. The main goal of Spring Data is to reduce the amount of boilerplate code required to implement CRUD operatios and data access.