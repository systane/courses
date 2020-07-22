package academy.learprogramming.companion_object

import java.time.Year

fun main() {
    println(CompanyCommunications.getTagLine())
    println(CompanyCommunications.getCopyrightLine())
}

//'object' keyword identifies an object class. This class declares and creates an instance at the same time,
//so that's why the object classes don't have constructors. We can use this class to create singletons in Kotlin
object CompanyCommunications {
    val currentYear = Year.now().value

    fun getTagLine() = "Out company rocks"
    fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our Company. All rights reserved."

}