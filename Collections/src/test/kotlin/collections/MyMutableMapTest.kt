package collections

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MyMutableMapTest {
    companion object {
        @JvmStatic
        fun mutableListSource() = listOf(MyHashMap<Int, Int>())
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When put 1 element, element then size is 1`(list: MyMutableMap<Int, Int>) {
        list.put(0, 0)
        Assertions.assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When put 10 element, element then size is 10`(list: MyMutableMap<Int, Int>) {
        repeat(10) { list.put(it, it) }
        Assertions.assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When put 100 element, element then size is 100`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        Assertions.assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list cleared then size is 0`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        list.clear()
        Assertions.assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list contains element then true`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        Assertions.assertTrue(list.containsValue(99))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list doesn't contain element then false`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        Assertions.assertFalse(list.containsValue(-1))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 element then it does not contain`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        list.remove(1)
        Assertions.assertFalse(list.containsValue(1))
        Assertions.assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed non existent element then nothing`(list: MyMutableMap<Int, Int>) {
        repeat(100) { list.put(it, it) }
        list.remove(999)
        Assertions.assertEquals(100, list.size)
    }
}