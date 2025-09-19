package collections

interface MyMutableList<T> {
    val size: Int

    fun add(element: T)
    fun add(index: Int, element: T)
    operator fun plus(element: T)
    operator fun minus(element: T)
    fun removeAt(index: Int)
    fun remove(element: T)
    operator fun get(index: Int): T
    fun clear()
    fun contains(element: T): Boolean
}