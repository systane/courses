# **ES6**

### How to declare variables in ES6

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
![Example 1 - var]()

In this case, it won't be possible to see the second output of `console.log()`, because `stageOfDevelopment`
was declared inside a function, and it is just visible there.

**var** can also be used to redeclare variables, like this:

    var age = 20;
    console.log(age); //age =  20;

    var age = 200; 
    console.log(age); //age = 200;

    age = 300;
    console.log(age); //age = 300


How about **let** and **const** ? How can we use them? 
Rather than been scoped to the function, `let` and `const` they are scoped to the block. And what's a block? A block is any code inside `{}`, as you can see in the below example:
 
    var age = 20;

    if(age >= 12 && age <= 20){
        let stageOfDevelopment = 'Adolescence'; 
        console.log(`You are ${age} years old.`);
    }

    console.log(`You are at ${stageOfDevelopment}`);

This modified code from the first example won't output the second console.log, because `stageOfDevelopment` has a block scope and it's visible by the `if(age >= 12 && age <= 20){}`. 

Another point that we must know, is that `let`you cannot redeclare the same variable in the same scope, you can just update the date inside it, like this:

    let age = 20;
    console.log(age); //age =  20;
    
    let age = 300; // --> error   

    age = 200; 
    console.log(age); //age = 200;

    if(age > 150){
        let age = 1000;
        console.log(age); // age = 1000;
    }

    console.log(age); //age = 200;

In this example, you can notice that if you redeclare 'let' in the same scope, it will throw an error on our console, but in a different scope (different block of code), it works normally.

Another interesting thing in `let` is when it is used in loop `for`. Let's look at the following example:

    for(let i = 0; i< 10; i++){
        console.log(i);
        setTimeout(function() {
            console.log('The number is ' + i);
        },1000);
    }

The default behavior of `let` binds the current value of each iteration and the second `console.log` output will show 0 through 9. This behavior cannot be reproduced with `var` because the entire loop will be executed before of the second `console.log`, and each iteration `i` will be overwritten. With `var` our second `console.log` will always output 10.

**const** works in the same way as `let` in terms of scope, the difference is that `const` cannot be updated. It's something that is constant.


