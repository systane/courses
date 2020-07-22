package academy.learprogramming.companion_object

//There is no 'static' keyword in Kotlin. So we cannot call a method without creating an instance of it, like this:
//SomeClass.accessPrivateVar(). To create an equivalent situation, we need to use Companion Objects.
class SomeClass {
    private val privateVar = 6

    fun accessPrivateVar() {
        println("I'm accessing privateVar: $privateVar")
    }
}

fun main() {
    println(SomeOtherClass.accessPrivateVar())

    //factory pattern
    val otherClass1 = OtherClass.justAssign("object 1")
    val otherClass2 = OtherClass.upperOrLoweCase("object 2 lowercase", true)
    val otherClass3 = OtherClass.upperOrLoweCase("object 3 lowercase", false)

    println(otherClass1.someString)
    println(otherClass2.someString)
    println(otherClass3.someString)

}

//We can create an equivalent situation using Companion Object, like this:
class SomeOtherClass {
    companion object {
        private val privateVar = 6

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"
    }
}

//We can create a factory class with private constructors using companion object:
class OtherClass private constructor(val someString: String) {
    companion object {
        private val privateVar = 6

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"

        fun justAssign(str: String) = OtherClass(str)

        fun upperOrLoweCase(str: String, lowerCase: Boolean): OtherClass {
            if (lowerCase)
                return OtherClass(str.toLowerCase())
            else
                return OtherClass(str.toUpperCase())
        }
    }
}