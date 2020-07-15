package academy.learprogramming.challenge1

fun main() {
    val hello1 = "Hello"
    val hello2 = "Hello"

    println("hello1 is referentially equals to hello2: ${hello1 === hello2}")
    println(hello1 == hello2) //structure equals

    //Smart Cast
    val text: Any = "The Any type is the root of the Kotlin hierarchy"
    if(text is String){
        println(text.toUpperCase())
    }

}