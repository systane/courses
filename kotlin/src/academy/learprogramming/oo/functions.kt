package academy.learprogramming.oo

fun main() {
    //all kotlin functions return Unit Object by default and we don't need to specified it.
    //This Unit object is like the void in Java.
    val dog1 = Dog("doguinho")
    println(dog1.upperCaseName())

    val dog2 = Dog("doguinho2")
    val dog3 = Dog("doguinho3")

    val manyDogs = arrayOf(dog1, dog2, dog3)
    printNames(*manyDogs) //* --> is the spread operator, it will unpack the array and pass the elements as individual arguments

    val dog4 = Dog("doguinho4")
    val moreDogs = arrayOf(*manyDogs, dog4) //spread operator help us to unpack the first array and combine it with dog4 in a new array
    printNames(*moreDogs)


    //Function Extension:
    val s = "testingFunctionExtensions"
    println(s.upperCaseFirstLetter())

    //inline functions: They're really good if used we lambda expressions, because the body of the function will be generated
    //in the bytecode
}

fun printNames(vararg names: Dog){
    for(name in names){
        println(name)
    }
}

//function extension: We create the illusion that this function belongs to the String Kotlin class
fun String.upperCaseFirstLetter() : String {
    val firstUpperLetter = this.substring(0, 1).toUpperCase()
    return firstUpperLetter + this.substring(1)
}

data class Dog (private val name: String){

    //by default functions are public and final in Kotlin
    fun upperCaseName() = name.toUpperCase()
}