package collections

class NumbersLinkedList : NumbersMutableList {

    class Node(
        val item: Int,
        var next: Node? = null
    )

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        if (size == 0) {
            val node = Node(number)
            first = node
            last = node
            size++
            return
        }
        val newElement = Node(number)
        last?.next = newElement
        last = newElement
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        if (index == size) {
            add(number)
            return
        }
        if (index == 0) {
            val node = Node(number, first)
            first = node
            size++
            return
        }
        val newElement = Node(number)
        val before = getNode(index - 1)
        val after = before.next
        before.next = newElement
        newElement.next = after
        if (after == null) {
            last = newElement
        }
        size++
    }

    override fun plus(number: Int) {
        add(number)
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        if (index == 0 && size == 1) {
            clear()
            return
        }
        if (index == 0) {
            first = first?.next
            size--
            return
        }
        val before = getNode(index - 1)
        val after = before.next?.next
        before.next = after
        if (after == null) {
            last = before
        }
        size--
    }

    override fun remove(number: Int) {
        if (first?.item == number) {
            removeAt(0)
            return
        }

        var before = first
        repeat(size) {
            val node = before?.next
            if (node?.item == number) {
                val after = node.next
                before.next = after
                if (after == null) {
                    last = before
                }
                size--
                return
            } else {
                before = before?.next
            }
        }
    }

    override fun get(index: Int): Int {
        return getNode(index).item
    }

    private fun getNode(index: Int): Node {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        var node = first
        repeat(index) {
            node = node?.next
        }
        return node!!
    }

    override fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun contains(number: Int): Boolean {
        var before = first
        repeat(size) {
            val node = before?.next
            if (node?.item == number) {
                return true
            } else {
                before = before?.next
            }
        }
        return false
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index, size: $size")
        }
    }

    private fun checkIndexForAdding(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index, size: $size")
        }
    }
}