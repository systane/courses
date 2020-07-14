package academy.learprogramming.helloworld


//typealias is just an alias for Types, we can create an alias for a Set of Employee
typealias EmployeeSet = Set<Employee>


//top level function are every function that are created without a class, so this main function is a top level function
fun main(args: Array<String>) {
    println("Hello World")

    //var is used to declare mutable variables
    var number: Int
    number = 10;
    number = 20;

    //val is used to declare immutable variables. We can assign a value for this kind of variables only once inside a block of code
    val employee1 = Employee("Cris Luiza", 500)
    employee1.name = "Cristiana Luiza"

    //if we try to reassign a new instance to employee1, we'll get an error, because val creates immutable variables.
    //employee1 = Employee("Cristiana", 5)

    val employee2: Employee
    val number2 = 100

    if(number < number2){
        employee2 = Employee("Luis Fernando", 100)
    }else{
        employee2 = Employee("Luis Carlos", 200)
    }

    //typealias example
    val employees: EmployeeSet

    val names = arrayListOf("Luis", "Cristiana", "Luis Carlos");
    print(names[1]) //is the same as names.get(1)

    //All exception in Kotlin are unchecked, so you don't need to throw it at the method signature
    //Ternary operator doesn't exist in Kotlin.

}

class Employee(var name: String, val id: Int){}