package calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {

    companion object {
        @JvmStatic // когда методы класса нужно вызвать из кода на Java
        private fun calculatorSource() = listOf(SimpleCalculator(), LoggingCalculator())
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `when add 5 to 10 then result 15`(calculator: Calculator) { // `здесь можно писать с пробелами`
        val result = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun whenAdd100To50Result150(calculator: Calculator) {
        val result = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `when 10 subtract 5 then result 5`(calculator: Calculator) {
        val result = calculator.subtraction(10, 5)
        val expected = 5
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun when100Subtract79Result21(calculator: Calculator) {
        val result = calculator.subtraction(100, 79)
        val expected = 21
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @_root_ide_package_.org.junit.jupiter.params.provider.MethodSource("calculatorSource")
    fun `when 10 multiply 5 then result 50`(calculator: Calculator) {
        val result = calculator.multiplication(10, 5)
        val expected = 50
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun when2Multiply7Result14(calculator: Calculator) {
        val result = calculator.multiplication(2, 7)
        val expected = 14
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `when 10 divide 5 then result 2`(calculator: Calculator) {
        val result = calculator.division(10, 5)
        val expected = 2
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun when90Divide15Result6(calculator: Calculator) {
        val result = calculator.division(90, 15)
        val expected = 6
        assertEquals(expected, result)
    }

    @Test
    fun testFloatNumbers() {
        var number = 0.0
        repeat(100) {
            number += 0.01
        }
        val expected = 1.0
        // при тестировании дробных чисел добавлять погрешность!
        assertEquals(expected, number, 0.0001) // 0.0001 - погрешность
    }
}