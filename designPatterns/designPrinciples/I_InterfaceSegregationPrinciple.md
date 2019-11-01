# **I - Interface Segregation Principle (ISP)**

"Make fine grained interfaces that are client specific" (Robert C. Martin). This principle is very direct, just implement specific interfaces and if you have a "fat" interface to assign, you might split it in grained interfaces.

Let's look a simple snippet that breaks this principle. In this example, our client `OldNokiaTijolao` doesn't need to implement the `takePicture()` method, and that's the problem with this snippet.
![PROBLEM_ISP](https://github.com/systane/courses/blob/master/designPatterns/img/ISP/PROBLEM_ISP.png)

We can refactory our code to looks like the bellow snippet. It was created a new interface `AdvancedCellPhone` just to support the funcionality of taking photos, so our `NormalCellPhone` interface becomes more grained and specific.
![SOLUTION_ISP](https://github.com/systane/courses/blob/master/designPatterns/img/ISP/SOLUTION_ISP.png)

Be carefull when applying this principle! Don't make overengineering and start to make a "boom" of interfaces in your project.
