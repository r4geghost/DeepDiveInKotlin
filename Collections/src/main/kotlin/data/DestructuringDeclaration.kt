package data

fun main() {
    val dictionary: List<MyPair<String, String>> = listOf(
        MyPair("hello", "bonjour"),
        MyPair("thank you", "merci")
    )

    // здесь используется метод component, позволяющий разбить объект класса на составляющие
    for ((first, second) in dictionary) {
        println("$first - $second")
    }

    // деструктуризация - получение свойств из объекта (которые можно сохранить в переменные)
    val (english, french) = Pair("hello", "bonjour")
    println(english)
    println(french)
}

// в data классах для каждого свойства класса создаются методы component(n)
// например в классе MyPair будут созданы методе component(1) и component(2), которые будут возвращать first и second
// поэтому от data классов нельзя наследоваться!
data class MyPair<F,S>(val first: F, val second: S)