package collections

import kotlin.math.abs

class NumbersHashSet : NumbersMutableSet {

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }

    class Node(
        val item: Int,
        var next: Node? = null
    )

    private var elements = arrayOfNulls<Node>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(number: Int): Boolean {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return add(number, elements).also { if (it) size++ }
    }

    override fun remove(number: Int) {
        val position = getElementPosition(number, elements.size)
        val existing = elements[position] ?: return

        if (existing.item == number) {
            elements[position] = existing.next
            size--
            return
        } else {
            var nextElement = existing.next ?: return
            while (true) {
                if (nextElement.item == number) {
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

    override fun contains(number: Int): Boolean {
        val position = getElementPosition(number, elements.size)
        var element = elements[position] ?: return false
        while (true) {
            if (element.item == number) {
                return true
            } else {
                element = element.next ?: return false
            }
        }
    }

    private fun getElementPosition(number: Int, arraySize: Int): Int {
        return abs(number % arraySize)
    }

    private fun increaseArray() {
        val array = arrayOfNulls<Node>(elements.size * 2)
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                add(currentElement.item, array)
                currentElement = currentElement.next
            }
        }
        elements = array
    }

    private fun add(number: Int, array: Array<Node?>): Boolean {
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