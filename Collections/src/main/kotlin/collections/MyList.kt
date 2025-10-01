package collections

interface MyList<out T> : MyCollection<T> {
    override val size: Int

    operator fun get(index: Int): T
    // @UnsafeVariance здесь корректна, т.к. мы не изменяем коллекцию вызовом метода contains
    override fun contains(element: @UnsafeVariance T): Boolean
}