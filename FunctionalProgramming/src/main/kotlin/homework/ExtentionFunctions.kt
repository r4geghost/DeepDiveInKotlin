package homework

// Напишите extension-функцию для класса List<Int>
// 1. Назовите её sumOfEvens.
// 2. Подсчитайте сумму всех чётных чисел в списке.
private fun List<Int>.sumOfEvens(): Int {
    var sum = 0
    for (num in this) {
        if (num % 2 == 0) {
            sum += num
        }
    }
    return sum
}

// Напишите функцию processList
// 1. Считайте строку чисел, введённую пользователем.
// 2. Преобразуйте строку в список целых чисел.
// 3. Вызовите extension-функцию sumOfEvens для списка.
// 4. Выведите результат вычисления.
private fun processList() {
    val numbers = readln().split(" ").map { it.toInt() }
    println("Сумма чётных чисел: ${numbers.sumOfEvens()}")
}

fun main() {
    processList()
}