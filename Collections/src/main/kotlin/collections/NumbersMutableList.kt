package collections

interface NumbersMutableList {
    val size: Int

    fun add(number: Int)
    fun get(index: Int): Int
}