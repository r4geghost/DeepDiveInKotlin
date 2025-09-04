package homework

// TODO: Определить функцию высшего порядка processNumbers
// 1. Функция должна принимать:
//    - Список чисел для обработки.
//    - Лямбда-выражение для фильтрации чисел (возвращает Boolean).
//    - Лямбда-выражение для преобразования чисел (возвращает Int).
// 2. Реализовать логику фильтрации чисел на основе переданного лямбда-выражения.
// 3. Преобразовать отфильтрованные числа с помощью второго лямбда-выражения.
// 4. Вернуть новый список с преобразованными числами.
private fun processNumbers(numbers: List<Int>, numberFilter: (Int) -> Boolean, numberTransformer: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for (number in numbers) {
        if (numberFilter(number)) {
            result.add(numberTransformer(number))
        }
    }
    return result
}


// TODO: Определить функцию startProcessing
// 1. Вывести сообщение: "Введите числа, разделенные пробелами:".
// 2. Считать строку чисел, введённую пользователем.
// 3. Преобразовать строку в список чисел.
// 4. Вызвать функцию processNumbers с:
//    - Условием: числа больше 10.
//    - Преобразованием: умножение числа на 3.
// 5. Вывести результат обработки в формате: "Результат обработки: [<результат>]".
private fun startProcessing() {
    println("Введите числа, разделенные пробелами:")
    val numbers = readln().split(" ").map { it.toInt() }

    val filteredNumbers = processNumbers(numbers, numberFilter = { it > 10 }, numberTransformer = { it * 3 })
    println("Результат обработки: $filteredNumbers")
}

fun main() {
    startProcessing()
}