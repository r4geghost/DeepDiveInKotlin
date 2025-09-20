package collections

class MyHashSet<T> : MyMutableSet<T> {

    companion object {
        private val PRESENT = Any()
    }

    private val map = MyHashMap<T, Any>()

    override val size: Int
        get() = map.size

    override fun add(element: T): Boolean = map.put(element, PRESENT) == null

    override fun remove(element: T) {
        map.remove(element)
    }

    override fun clear() = map.clear()

    override fun iterator(): Iterator<T> = map.keys.iterator()

    override fun contains(element: T): Boolean = map.containsKey(element)
}