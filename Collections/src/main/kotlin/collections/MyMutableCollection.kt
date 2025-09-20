package collections

interface MyMutableCollection<T> : MyCollection<T> {
    override val size: Int
    fun add(element: T): Boolean
    fun remove(element: T)
    fun clear()
    override fun contains(element: T): Boolean
}