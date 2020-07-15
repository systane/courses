package academy.learprogramming.helloworld

fun main() {
    //we create an array of length 16, and we fill it with evenNumbers (lambda expression)
    val numbers = Array(16) {i -> i * 2}
    for (number in numbers){
        println(number)
    }

    val mixedArray = arrayOf("aaaaa", 20, 20.3)
    for (value in mixedArray){
        println(value)
    }

    //we need to explicit tell that str can be null, and we do that by typing a ? after the data type
    val str: String? = "this isn't null"
    println(str?.toUpperCase())

    //we can use the safe operator to access object's properties without getting a NullPointerException
    val strNull: String? = null
    println(strNull?.toUpperCase()) //prints null

    //we can use the Elvis operator to choose a default value instead of getting "null"
    val str2 = strNull ?: "this is a default value"
    println(str2.toUpperCase())

}