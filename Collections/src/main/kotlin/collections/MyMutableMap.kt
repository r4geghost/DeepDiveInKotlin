package collections

interface MyMutableMap<K, V> {
    val size: Int
    val keys: MySet<K>
    val values: MyCollection<V>
    fun put(key: K, value: V): V?
    operator fun get(key: K): V?
    fun remove(key: K): V?
    fun clear()
    fun containsKey(key: K): Boolean
    fun containsValue(value: V): Boolean
}