package academy.learprogramming.oo

fun main() {
    val student = Student("Cris")
    println(student.firstName)
    println(student.fullTime)

    val student2 = Student("João", false)
    println(student2.firstName)
    println(student2.fullTime)
}

//we can create constructors we default value parameters.
private class Student (val firstName: String, var fullTime: Boolean = true) {

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