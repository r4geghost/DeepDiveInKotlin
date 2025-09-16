package collections

interface NumbersMutableList {
    val size: Int

    fun add(number: Int)
    fun add(index: Int, number: Int)
    fun removeAt(index: Int)
    fun remove(number: Int)
    fun get(index: Int): Int
    fun clear()
    fun contains(number: Int): Boolean
}