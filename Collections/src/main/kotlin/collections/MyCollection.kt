package collections

interface MyCollection<out T> : Iterable<T> {
    val size: Int
    fun contains(element: @UnsafeVariance T): Boolean
}