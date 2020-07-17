package academy.learprogramming.oo

val MY_CONSTANT = 100

fun main() {

    println(MY_CONSTANT)
    val car = Car("blue", "HB20", 2020)
    println(car)

    val car2 = car.copy(year = 2016)
    println(car2)
}

//data classes come with toString, equals and hashCode function implemented by default (just by the properties used in
// the constructor), but can create your custom version overriding those functions. Data classes can't be abstract,
// sealed or inner classes.
data class Car(val  color: String, val model: String, val year: Int) {

}