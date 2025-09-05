package test

fun main() {
    val number = readln().toInt()
    println(isPositiveFunction(number))
    println(number.isPositive())
}

// обычная функция
private fun isPositiveFunction(number: Int) = number > 0

// функция расширения (extension function)
// под капотом - обычная функция с параметром
private fun Int.isPositive() = this > 0