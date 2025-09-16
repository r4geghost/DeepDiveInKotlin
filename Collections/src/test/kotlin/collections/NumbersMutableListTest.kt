package collections

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableListTest {

    companion object {
        @JvmStatic
        fun mutableListSource() = listOf(NumbersArrayList())
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: NumbersArrayList) {
        list.add(0)
        Assertions.assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 element then size is 10`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        Assertions.assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 5th element then result it correct`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        Assertions.assertEquals(5, list.get(5))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 100 element then size is 100`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        Assertions.assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 50th element then result it correct`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        Assertions.assertEquals(50, list.get(50))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element removed then size decreased`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.removeAt(50)
        Assertions.assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed 50th element then next value at this positions`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.removeAt(50)
        Assertions.assertEquals(51, list.get(50))
    }
}