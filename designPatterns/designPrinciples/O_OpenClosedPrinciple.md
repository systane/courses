# **O - Open/Closed Principle (OCP)**

Uncle Bob preaches that "Software entities (classes, modules, functions, etc) should be open for extension, but closed for modification". What this mean? I must start to use inheritance extension every time that I must modify a class to support a new funcionality? No. Uncle Bob is not specifying that you should use inheritance. This extension he is referring to is the abstract concept of extension, you can create extensions extending your class using inheritance, interfaces, abstract classes or composition. Let's look an example to make things clearer.

