# **L - Liskov Substituition Principle (LSP)**

The third letter of the acronym SOLID preaches that "Derived classes must be substitutable for their base classes". Stop and read it again. With this phrase, Uncle Bob simplified the scientific definition that Barbara Liskov introduced in a published paper in 1987 (Yeah, this is a very old concept). 

Let's look two snippets of code to better understand this definition.

![accountClasses](https://github.com/systane/courses/blob/master/designPatterns/img/LSP/PROBLEM_LSP.png)

Let's suppose that `AccountDAO.getAllAccounts()` return all account records saved in the database, which can be both BasicAccount or SalaryAccount. So when the application start to go through the `accountList` and execute the `yield()` method will thrown an Exception and our program will break. In this case **we are not respecting the Liskov Substituition Principle**, because the derived class (SalaryAccount) can't be substitutable for the base class (BasicAccount). This happens because we implemented a stricter validation rules in the subclass.
![main](https://github.com/systane/courses/blob/master/designPatterns/img/LSP/main_LSP.png)



LSP improve the Open/Close principle, because when you derived classes that can be substitutable for their base classes, we're able to extend the base classes without making modification in the subclasses.

It's important observe two things:
1. A subclass (derived class) doesn't implement stricter validation rules on input parameters than the parent class.
1. Apply the same rules to all output parameters as applied by the parent class.