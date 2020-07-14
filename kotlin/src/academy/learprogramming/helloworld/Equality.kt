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

    println("operator for instance of")
    val something: Any = student4 //creates a something val with the type Any and initialize it as a student4 instance
    if(something is Student){ // !is is the negative operator for instance of
        val newStudent = something as Student
        println(newStudent.toString())
    }

    val change = 4.22
    //how print $change if we declared a variable called change? Simple, just use \$change to escape the $ sign
    println("To show the value of change, we use \$change")
    println("Your change is $$change")
    println("Your change is $change")

    val numerator = 20
    val denominator = 10
    println("the value of $numerator divided by $denominator is ${numerator/denominator}")
    println("the name of the student4 is ${student4.name}")

}

class Student(var name: String, val id: Int){

    override fun equals(other: Any?): Boolean {
        if(other is Student){ //'is' operator is the same as 'instance of'
            return other.id == this.id && other.name == this.name
        }

        return false
    }

    override fun toString(): String {
        return "Student(name=$name, id=$id)"
    }


}