package academy.learprogramming.helloworld

fun main(args: Array<String>) {
    val student1 = Student("Luis Fernando", 1)
    val student2 = Student("Cris", 2)
    val student3 = Student("Cris", 2)

    println("Using equals operator")
    println(student1 == student2)
    //the operator == is the same as 'equals' it checks for structure equality, not reference equality as in Java
    println(student2 == student3)
    println(student1.equals(student2))
    println(student2.equals(student3))

    //If we want to check for instance equality, we need to use the operator ===
    val student4 = student2;
    println(student4 === student2)
    println(student1 === student2)
    println(student2 === student3)

    println("Using not equals operator")
    println(student4 !== student2)
    println(student4 != student2)
    println(student2 != student3)
    println(student2 !== student3)

}

class Student(var name: String, val id: Int){

    override fun equals(other: Any?): Boolean {
        if(other is Student){
            return other.id == this.id && other.name == this.name
        }

        return false
    }

}