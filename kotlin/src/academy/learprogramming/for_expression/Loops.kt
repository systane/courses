package academy.learprogramming.for_expression


fun main() {
    val range = 1..5
    for (i in range) //loop for in kotlin
        println(i)

    val stringRange = "ABD".."XYZ"
    val charRange = 'a'..'z'

    println('q' in charRange)
    println("CC" in stringRange)
    println("CCCCCCCC" in stringRange)
    println("ZZ" in stringRange) //ZZ is out of the stringRange, that's why we got a false here

    for (i in 20 downTo 15) { //reverse for
        println(i)
    }

    for (i in 1..20 step 2){ //for loop with step of 2
        println(i)
    }

    for (i in 1 until 10) { //for loop from 1 to 9
        println(i)
    }

    val seasons = arrayOf("spring", "summer", "winter", "fall")
    for (season in seasons){
        println(season)
    }

    val notASeason = "whatever" !in seasons
    println(notASeason)

    for(index in seasons.indices){
        println("${seasons[index]} is season number $index")
    }
}