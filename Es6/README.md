# **ES6**

#### How to declare variables in ES6

Basically, we have three ways to declare variables in ES6. We can use: `var`, `let` and `const`, to reserve space in the computer memory. Although they do the same thing, there are some differences between them.

We can declare a variable in the old school way  using **var:** `var someVariable = 10;`. But attention, **var** has a function scope, when declared inside a function, but in any different situation, it has a global scope. Let's take a look.

    var age = 20;

    if(age >= 12 && age <= 20){
        var stageOfDevelopment = 'Adolescence'; 
        console.log(`You are ${age} years old.`);
    }

    console.log(`You are at ${stageOfDevelopment}`);

In the above code, it is possible to see all the console.log output, because `age` and `stafeOfDevelopment` have global scope. But if you wondering about the function scope, take a look at below example:

    var age = 20;

    function showStage(){
        if(age >= 12 && age <= 20){
            var stageOfDevelopment = 'Adolescence'; 
            console.log(`You are ${age} years old.`);
        }
    }

    showSate();

    console.log(`You are at ${stageOfDevelopment}`);

In this case, it won't be possible to see the second output of `console.log()`, because `stageOfDevelopment`
was declared inside a function, and it is just visible there.

**var** can also be used to redeclare variables, like this:

    var age = 20;
    console.log(age); //outputs 20;

    var age = 200; //redeclares age to 200;
    console.log(age);

    age = 300;//updates age to 300
    console.log(age);





