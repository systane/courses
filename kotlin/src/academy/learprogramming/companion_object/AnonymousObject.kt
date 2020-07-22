package academy.learprogramming.companion_object

fun main() {

    //we can create anonymous instances using the object keyword:
    wantsSomeInterface(object : SomeInterface{
        override fun mustImplement(num: Int): String = "This is from mustImplement: ${num * 100}"
    })

}

interface SomeInterface{
    fun mustImplement(num: Int): String
}

fun wantsSomeInterface (si: SomeInterface) {
    println("Printing from wantsSomeInterface ${si.mustImplement(22)}")
}