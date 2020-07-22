package academy.learprogramming.if_expression

fun main() {
    val someCondition = 69 < 22

    //If in kotlin can return a value, so we don't need the ternary operator anymore
    val num = if(someCondition) 50 else 592
    println(num)

    val num2 = if(someCondition){
        println("somehing")
        50
    } else {
        println("somehing else")
        592
    }
    println(num2)
}