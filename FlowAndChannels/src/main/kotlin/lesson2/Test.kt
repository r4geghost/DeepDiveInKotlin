package lesson2

import kotlin.random.Random

fun main() {
    // создание последовательности от коллекции через asSequence()
    val sequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asSequence()
    // создание последовательности через generateSequence (потока, sequence)
    val sequence1 = generateSequence(0 /* здесь указываем первый элемент */) {
        it + 1 // здесь описываем, как генерировать следующие элементы
    }
    // другой способ
    val sequence2 = generateSequence {
        Random.nextInt(100)
    }
    // более гибкий способ
    val sequence3 = sequence<Int> {
        println("Start generation...")
        yield(0) // suspend функция, но scope здесь ограниченный (определенный набор)
        println("Generate random numbers...")
        repeat(100) {
            yield(Random.nextInt()) // suspend функция, но scope здесь ограниченный (определенный набор)
        }
    }

    sequence3.filter { it % 2 == 0 }
        .map { it * it }
        .take(10) // если убрать этот оператор, поток будет бесконечно создавать элементы
        .forEach(::println)
}