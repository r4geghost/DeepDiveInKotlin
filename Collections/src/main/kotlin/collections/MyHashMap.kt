package collections

import kotlin.math.abs

class MyHashMap<K, V> : MyMutableMap<K, V> {

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }

    data class Node<K, V>(
        val key: K,
        var value: V,
        var next: Node<K, V>? = null
    )

    private var elements = arrayOfNulls<Node<K, V>>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override val keys: MySet<K>
        get() = MyHashSet<K>().apply {
            foreach { add(it.key) }
        }

    override val values: MyCollection<V>
        get() = MyArrayList<V>().apply {
            foreach { add(it.value) }
        }

    override fun get(key: K): V? {
        val position = getElementPosition(key, elements.size)
        var existing = elements[position] ?: return null
        while (true) {
            if (existing.key == key) {
                return existing.value
            } else {
                existing = existing.next ?: return null
            }
        }
    }

    override fun put(key: K, value: V): V? {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return put(key, value, elements).also { if (it == null) size++ }
    }

    override fun remove(key: K): V? {
        val position = getElementPosition(key, elements.size)
        val existing = elements[position] ?: return null

        if (existing.key == key) {
            elements[position] = existing.next
            size--
            return existing.value
        } else {
            var nextElement = existing.next ?: return null
            while (true) {
                if (nextElement.key == key) {
                    existing.next = nextElement.next
                    size--
                    return nextElement.value
                } else {
                    nextElement = nextElement.next ?: return null
                }
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun containsKey(key: K): Boolean {
        val position = getElementPosition(key, elements.size)
        var existing = elements[position] ?: return false
        while (true) {
            if (existing.key == key) {
                return true
            } else {
                existing = existing.next ?: return false
            }
        }
    }

    override fun containsValue(value: V): Boolean {
        foreach {
            if (it.value == value) {
                return true
            }
        }
        return false
    }

    private fun getElementPosition(key: K, arraySize: Int): Int {
        return abs(key.hashCode() % arraySize)
    }

    private fun increaseArray() {
        val array = arrayOfNulls<Node<K, V>>(elements.size * 2)
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                put(currentElement.key, currentElement.value, array)
                currentElement = currentElement.next
            }
        }
        elements = array
    }

    private fun put(key: K, value: V, array: Array<Node<K, V>?>): V? {
        val newElement = Node(key, value)
        val position = getElementPosition(key, array.size)

        var existing = array[position]
        if (existing == null) {
            array[position] = newElement
            return null
        } else {
            while (true) {
                if (existing?.key == key) {
                    return existing?.value.also { existing?.value = value }
                } else {
                    if (existing?.next == null) {
                        existing?.next = newElement
                        return null
                    } else {
                        existing = existing.next
                    }
                }
            }
        }
    }

    private inline fun foreach(operation: (Node<K, V>) -> Unit) {
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                operation(currentElement)
                currentElement = currentElement.next
            }
        }
    }
}