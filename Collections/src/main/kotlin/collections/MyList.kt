package collections

interface MyList<T> : MyCollection<T> {
    override val size: Int

    operator fun get(index: Int): T
    override fun contains(element: T): Boolean
}