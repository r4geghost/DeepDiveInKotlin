package calculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `when add 5 to 10 then result 15`() { // `здесь можно писать с пробелами`
        val result = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }

    @Test
    fun whenAdd100To50Result150() {
        val result = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @Test
    fun `when 10 subtract 5 then result 5`() {
        val result = calculator.subtraction(10, 5)
        val expected = 5
        assertEquals(expected, result)
    }

    @Test
    fun when100Subtract79Result21() {
        val result = calculator.subtraction(100, 79)
        val expected = 21
        assertEquals(expected, result)
    }

    @Test
    fun `when 10 multiply 5 then result 50`() {
        val result = calculator.multiplication(10, 5)
        val expected = 50
        assertEquals(expected, result)
    }

    @Test
    fun when2Multiply7Result14() {
        val result = calculator.multiplication(2, 7)
        val expected = 14
        assertEquals(expected, result)
    }

    @Test
    fun `when 10 divide 5 then result 2`() {
        val result = calculator.division(10, 5)
        val expected = 2
        assertEquals(expected, result)
    }

    @Test
    fun when90Divide15Result6() {
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