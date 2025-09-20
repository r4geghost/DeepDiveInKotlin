package collections

interface MySet<T> : MyCollection<T> {
    override val size: Int

    override fun contains(element: T): Boolean
}