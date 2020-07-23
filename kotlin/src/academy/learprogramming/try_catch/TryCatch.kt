package academy.learprogramming.try_catch

import java.lang.IllegalArgumentException

fun main() {
    //TryCatch can be used as expressions
    //In Kotlin we don't need to declare the exceptions that functions throws,
    // because Kotlin doesn't distinguish between checked and unchecked exception

    println(getNumber("22"))
    println(getNumber("22.5"))

    println(getNumber2("22.5") ?: "I can't print the result")
    println(getNumber2("22.5") ?: throw IllegalArgumentException("Number isn't an Int"))
}

fun getNumber(str: String): Int {
    return try {
        Integer.parseInt(str)
    } catch (e: NumberFormatException) {
        0
    }
}

fun getNumber2 (str: String) : Int? {
    return try {
        Integer.parseInt(str)
    }
    catch (e: java.lang.NumberFormatException) {
        null
    }
}