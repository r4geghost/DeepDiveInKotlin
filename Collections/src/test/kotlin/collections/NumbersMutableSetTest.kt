package collections

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableSetTest {
    companion object {
        @JvmStatic
        fun mutableListSource()= listOf(NumbersHashSet())
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: NumbersMutableSet) {
        list.add(0)
        Assertions.assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 element then size is 10`(list: NumbersMutableSet) {
        repeat(10) { list.add(it) }
        Assertions.assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 100 element then size is 100`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        Assertions.assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list cleared then size is 0`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        list.clear()
        Assertions.assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list contains element then true`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        Assertions.assertTrue(list.contains(99))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list doesn't contain element then false`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        Assertions.assertFalse(list.contains(-1))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 element then it does not contain`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        list.remove(1)
        Assertions.assertFalse(list.contains(1))
        Assertions.assertEquals(99,list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed non existent element then nothing`(list: NumbersMutableSet) {
        repeat(100) { list.add(it) }
        list.remove(999)
        Assertions.assertEquals(100,list.size)
    }
}