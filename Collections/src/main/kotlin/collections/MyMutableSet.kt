package collections

interface MyMutableSet<T> : MySet<T>, MyMutableCollection<T> {
    override val size: Int

    override fun add(element: T): Boolean
    override fun remove(element: T)
    override fun clear()
    override fun contains(element: T): Boolean
}