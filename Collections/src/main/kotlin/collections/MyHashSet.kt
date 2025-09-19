package collections

import kotlin.math.abs

class MyHashSet<T> : MyMutableSet<T> {

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }

    data class Node<T>(
        val item: T,
        var next: Node<T>? = null
    )

    private var elements = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return add(element, elements).also { if (it) size++ }
    }

    override fun remove(element: T) {
        val position = getElementPosition(element, elements.size)
        val existing = elements[position] ?: return

        if (existing.item == element) {
            elements[position] = existing.next
            size--
            return
        } else {
            var nextElement = existing.next ?: return
            while (true) {
                if (nextElement.item == element) {
                    existing.next = nextElement.next
                    size--
                    return
                } else {
                    nextElement = nextElement.next ?: return
                }
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(element: T): Boolean {
        val position = getElementPosition(element, elements.size)
        var existing = elements[position] ?: return false
        while (true) {
            if (existing.item == element) {
                return true
            } else {
                existing = existing.next ?: return false
            }
        }
    }

    private fun getElementPosition(element: T, arraySize: Int): Int {
        return abs(element.hashCode() % arraySize)
    }

    private fun increaseArray() {
        val array = arrayOfNulls<Node<T>>(elements.size * 2)
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                add(currentElement.item, array)
                currentElement = currentElement.next
            }
        }
        elements = array
    }

    private fun add(number: T, array: Array<Node<T>?>): Boolean {
        val newElement = Node(number)
        val position = getElementPosition(number, array.size)

        var existing = array[position]
        if (existing == null) {
            array[position] = newElement
            return true
        } else {
            while (true) {
                if (existing?.item == number) {
                    return false
                } else {
                    if (existing?.next == null) {
                        existing?.next = newElement
                        return true
                    } else {
                        existing = existing.next
                    }
                }
            }
        }
    }
}