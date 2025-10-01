package collections

interface MyMap<out K, out V> {
    val size: Int
    val keys: MySet<K>
    val values: MyCollection<V>
    operator fun get(key: @UnsafeVariance K): V?
    fun containsKey(key: @UnsafeVariance K): Boolean
    fun containsValue(value: @UnsafeVariance V): Boolean
}