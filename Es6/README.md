# **ES6**

#### How to declare variables in ES6

Basically, we have three ways to declare variables in ES6. We can use: `var`, `let` and `const`, to reserve a space in the computer memory. Although they do the same thing, there are some differences between them.

We can declare a variable using the old school way  using **var** `var someVariable = 10;`. But attention, **var** has a function scope, when declared inside a function, but in another caso it has a global scope. Lets take a look.

    var age = 20;

    if(age >= 12 && age <= 20){
        var stageOfDevelopment = 'Adolescence'; 
        console.log(`You are ${age} years old.`);
    }

    console.log(`You are at ${stageOfDevelopment}`);

In the above code, it will be possible to see all the console.log output, becase `age` and `stafeOfDevelopment` have global scope.

