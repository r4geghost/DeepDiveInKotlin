package homework

import kotlin.system.measureTimeMillis

fun main() {
    val iterationCount = 10_000_000

    // --- Вспомогательные функции для замеров времени выполнения ---
    val smallFunctionTime = measureTimeMillis {
        var resultSum = 0
        for (i in 0 until iterationCount) {
            resultSum += executeOperation(i, 1) { a, b -> a + b }
        }
        println("Результат для маленькой не inline функции: $resultSum")
    }

    val inlineSmallFunctionTime = measureTimeMillis {
        var resultSum = 0
        for (i in 0 until iterationCount) {
            resultSum += inlineExecuteOperation(i, 1) { a, b -> a + b }
        }
        println("Результат для маленькой inline функции:    $resultSum")
    }

    println("--- Производительность маленьких функций высшего порядка ---")
    println("No inline время: $smallFunctionTime мс")
    println("Inline время:    $inlineSmallFunctionTime мс\n")

    val bigFunctionTime = measureTimeMillis {
        var resultTotal = 0
        for (i in 0 until iterationCount) {
            resultTotal += performComplexCalculation(i)
        }
        println("Результат для большой не inline функции: $resultTotal")
    }

    val inlineBigFunctionTime = measureTimeMillis {
        var resultTotal = 0
        for (i in 0 until iterationCount) {
            resultTotal += inlinePerformComplexCalculation(i)
        }
        println("Результат для большой inline функции:    $resultTotal")
    }

    println("--- Производительность больших функций ---")
    println("No inline время: $bigFunctionTime мс")
    println("Inline время:    $inlineBigFunctionTime мс")

    // Результат для маленькой не inline функции: -2004260032
    // Результат для маленькой inline функции:    -2004260032
    // --- Производительность маленьких функций высшего порядка ---
    // No inline время: 18 мс
    // Inline время:    5 мс
    //
    // Результат для большой не inline функции: -1813197045
    // Результат для большой inline функции:    -1813197045
    // --- Производительность больших функций ---
    // No inline время: 361 мс
    // Inline время:    410 мс
}

/**
 * Маленькая функция высшего порядка (без inline).
 * Выполняет переданную лямбду operation над двумя числами.
 */
fun executeOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

/**
 * Маленькая inline функция высшего порядка.
 * Выполняет переданную лямбду operation над двумя числами.
 */
inline fun inlineExecuteOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

/**
 * "Большая" функция без inline.
 * Выполняет сложные вычисления с использованием массива и циклов.
 */
fun performComplexCalculation(value: Int): Int {
    var accumulator = value
    val factors = IntArray(10) { it * accumulator }
    for (i in factors.indices) {
        accumulator += (factors[i] + i) * 3 - (accumulator % 5)
        if (accumulator % 7 == 0) {
            accumulator -= i
        }
    }
    return if (accumulator % 2 == 0) {
        accumulator / 2
    } else {
        accumulator * 2
    }
}

/**
 * "Большая" inline функция.
 * Выполняет сложные вычисления с использованием массива и циклов.
 * Expected performance impact from inlining is insignificant. Inlining works best for functions with parameters of functional types
 */
inline fun inlinePerformComplexCalculation(value: Int): Int {
    var accumulator = value
    val factors = IntArray(10) { it * accumulator }
    for (i in factors.indices) {
        accumulator += (factors[i] + i) * 3 - (accumulator % 5)
        if (accumulator % 7 == 0) {
            accumulator -= i
        }
    }
    return if (accumulator % 2 == 0) {
        accumulator / 2
    } else {
        accumulator * 2
    }
}