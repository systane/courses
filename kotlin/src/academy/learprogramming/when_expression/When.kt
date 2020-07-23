package academy.learprogramming.when_expression

import java.math.BigDecimal

fun main() {
    //when keyword works like a switch statement
    val num = 201

    when (num) {
        100, 600 -> println("600")
        in 200..299 -> println("in range 200..299")
        800 -> println("800")
        else -> println("doesn't match anything")
    }

    val y = 10

    when (num) {
        y + 80 -> println("90") //we can use expression
        in 200..299 -> println("in range 200..299")
        800 -> println("800")
        else -> println("doesn't match anything")
    }


    //We can simplify the following if-else statement using when expression
    val obj: Any = "I'm a string"
    val obj2: Any = BigDecimal(25.2)
    val obj3: Any = 45

    val something: Any = obj2
    if (something is String) {
        println(something.toUpperCase())
    } else if (something is BigDecimal) {
        println(something.remainder(BigDecimal(10.5)))
    } else if (something is Int) {
        println("${something - 22}")
    }

    //The above if-else can be rewrite as following:
    when (something) {
        is String -> println(something.toUpperCase())
        is BigDecimal -> println(something.remainder(BigDecimal(10.5)))
        is Int -> println("${something - 22}")
    }

    val num2 = -50
    when {
        num < num2 -> println("num is less than num2")
        num > num2 -> println("num is greater than num2")
        else -> println("num is equals num2")
    }
}