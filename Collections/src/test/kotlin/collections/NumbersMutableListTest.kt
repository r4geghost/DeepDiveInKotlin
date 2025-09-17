package collections

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
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
    fun `When element added to first position then it is in first position`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.add(0, 999)
        Assertions.assertEquals(999, list[0])
        Assertions.assertEquals(101, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to last position then it is in last position`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.add(100, 999)
        Assertions.assertEquals(999, list[100])
        Assertions.assertEquals(101, list.size)
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
        Assertions.assertEquals(5, list[5])
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
        Assertions.assertEquals(50, list[50])
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
        Assertions.assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 element then next value at this positions`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.remove(50)
        Assertions.assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list cleared then size is 0`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list.clear()
        Assertions.assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list contains element then true`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        Assertions.assertTrue(list.contains(99))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list doesn't contain element then false`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        Assertions.assertFalse(list.contains(-1))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 element using plus operator then size is 10`(list: NumbersArrayList) {
        repeat(10) { list + it }
        Assertions.assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed 50th element using minus operator then next value at this positions`(list: NumbersArrayList) {
        repeat(100) { list.add(it) }
        list - 50
        Assertions.assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get wrong element index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list[10] }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get negative element index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list[-1] }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add invoked with wrong index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list.add(11, 1000) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add invoked with negative index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list.add(-1, 1000) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When remove invoked with wrong index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list.removeAt(11) }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When remove invoked with negative index then throw IndexOutOfBoundsException`(list: NumbersArrayList) {
        repeat(10) { list.add(it) }
        assertThrows<IndexOutOfBoundsException> { list.removeAt(-1) }
    }
}