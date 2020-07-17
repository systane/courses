package academy.learprogramming.oo

fun main() {
    println("============ STUDENT ===============")
    val student = Student("Cris", true, "thirdProperty")
    println(student.firstName)
    println(student.fullTime)

    println("============ STUDENT 2 ===============")
    val student2 = Student("João", false, "thirdProperty")
    println(student2.firstName)
    println(student2.fullTime)
    println(student2.thirdProperty)

    student2.thirdProperty = "another value for the thirdProperty"
    println(student2.thirdProperty)
}

//we can create constructors with default value parameters.
private class Student (val firstName: String, var fullTime: Boolean = true, thirdProperty: String) {

    //under the covers, Kotlin generate getters and setters for public class properties
    //we can create our own custom getter/setter, but for that, we need to explicit create this property, like this:
    var thirdProperty = thirdProperty
    get() {//we MUST create a getter just after we declare our property
        println("Running custom get")

        //our customer accessor (getter/setter) can't return or modify our property directly, to access the value of our
        //property, we need to use a backing field (identified by the work 'field')
        return field
    }

    set(value) {
        println("Running customer set")
        field = value
    }
}


/**LONG WAY: we can use the init block to initialize our Student class
private class Student constructor(firstName: String) {

val firstName: String

//we can use the init block to initialize our Student class. This block works like the body of a Java constructor
init {
this.firstName = firstName
}
}
 **/


/** INTERMEDIATE WAY: We can initialize our property as soon as we declare it.
private class Student constructor(firstName: String) {

val firstName: String = firstName

}
 **/


/**SHORT WAY: we can make create a property and initialize at the constructor level.
//OBS: The word constructor is optional. The constructor word are going to be required only when you change de visibility
//or if you need to put some annotation
private class Student constructor(val firstName: String) {

    var fullTime: Boolean = true

    //we can create secondary constructors, but to do that we need to delegate to the primary constructor
    //OBS: Secondary constructor doesn't create properties, it just assign! In this case, if we want to use the fullTime property
    //first, we need to create it.
    constructor(firstName: String, fullTime: Boolean) : this(firstName) {
        this.fullTime = fullTime
    }

}
**/