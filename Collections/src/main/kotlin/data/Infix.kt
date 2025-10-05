package data

fun main() {
    // to - extension функция на класс Pair
    val dictionary = listOf(
        "hello" to "bonjour", // первый способ вызова - инфиксный - infix
        "thank you".to("merci") // второй способ вызова - обычный
    )

    val otherDictionary: List<MyPair<String, Int>> = listOf(
        "one".myTo(1),
        "two" myTo 2 // слева объект, у которого вызывается функция, справа - параметр функции
    )

    for ((first, second) in otherDictionary) {
        println("$first - $second")
    }
}

// свой вариант реализации функции to
// аргумент должен быть один и не должен принимать значений по умолчанию
private infix fun <F, S> F.myTo(second: S) = MyPair(this, second)