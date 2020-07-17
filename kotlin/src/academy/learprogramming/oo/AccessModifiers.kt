package academy.learprogramming.oo

fun main() {
    val employee = Employee()
}

//we can declare private classes in Kotlin, private classes are visible for classes in the same file. In Kotlin, we can
//create classes with different name of the file, so make sense that kind of access modifier exists in Kotlin, but in Java
//we wouldn't want to have a private class.
//All classes in Kotlin are public and final by default.
private class Employee {

}

/** Top Level Access Modifiers:
-If you do not specify any visibility modifier, public is used by default, which means that your declarations will be visible everywhere;
-If you mark a declaration private, it will only be visible inside the file containing the declaration;
-If you mark it internal, it is visible everywhere in the same module;
-protected is not available for top-level declarations.

For members declared inside a class:
-private means visible inside this class only (including all its members);
-protected — same as private + visible in subclasses too;
-internal — any client inside this module who sees the declaring class sees its internal members;
-public — any client who sees the declaring class sees its public members.
**/