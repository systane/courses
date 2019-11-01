# **O - Open/Closed Principle (OCP)**

Uncle Bob preaches that "Software entities (classes, modules, functions, etc) should be open for extension, but closed for modification". What this mean? I must start to use inheritance extension every time that I must modify a class to support a new funcionality? No. Uncle Bob is not specifying that you should use inheritance. This extension he is referring to is the abstract concept of extension, you can create extensions extending your class using inheritance, interfaces, abstract classes or composition. Let's look an example to make things clearer.

![PROBLEM_OCP](https://github.com/systane/courses/blob/master/designPatterns/img/OCP/Problem_OCP.png)

In this example above, we have a DTO (Data Transfer Object) to receive the parameters of incoming requests. After that, I created a class Authentication that is responsible to make the login of the user using two different strategies. The first is using OAuth with Google authentication, and the second is a normal authentication using the client's email and password send in the request. That's ok, right? No. Imagine that now we need to support OAuth authentication with Facebook, Twitter and Instagram. Our Authentication class will become a bunch of if statements to check if the provider is Facebook, Google, etc. The real problem here is that our class is open, we didn't close it for modifications, so we can't even try to solve this problem without making a big refactory.

Let's close this classe creating an interface with the abstract method `login(ClientDTO client)` so in this way, we can put each different strategy of authentication in its own class, with this we automatically closed our main class AuthenticateLogin. The interface and its subclasses are listed bellow

![interface_OCP](https://github.com/systane/courses/blob/master/designPatterns/img/OCP/interface_OCP.png)

At the last, this is our authentication class. The client who calls hte login method will also choose which authentication strategy he wants to use, so our class can just call the login method. Look close to the `@AllArgsConstructor` and the private element `Authentication` declared used in this class. They together form another important SOLID principle. I will talk about it later.

![authenticateLogin_OCP](https://github.com/systane/courses/blob/master/designPatterns/img/OCP/authenticateLogin_OCP.png)