package academy.learprogramming.inheritance

fun main() {
    val laserPrinter = LaserPrinter("hp3333")
    laserPrinter.printModel()
}

abstract class Printer(val modelName: String) {

    open fun printModel() = println("The model is $modelName")
    abstract fun bestSellingPrice(): Double
}

class LaserPrinter(modelName: String): Printer(modelName) {
    override fun printModel() = println("The model of this LaserPrinter is $modelName")

    override fun bestSellingPrice(): Double = 1291.99
}