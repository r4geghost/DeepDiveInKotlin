package collections

class NumbersLinkedList : NumbersMutableList {

    class Node(
        var prev: Node? = null,
        val item: Int,
        var next: Node? = null
    )

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        val prevLast = last
        last = Node(prevLast, number)
        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        if (index == size) {
            add(number)
            return
        }
        if (index == 0) {
            val node = Node(null, number, first)
            first?.prev = node
            first = node
            size++
            return
        }
        val before = getNode(index - 1)
        val after = before.next
        val newElement = Node(before, number, after)
        before.next = newElement
        after?.prev = newElement
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
        val node = getNode(index)
        unlink(node)
    }

    override fun remove(number: Int) {
        var node = first
        repeat(size) {
            if (node?.item == number) {
                node?.let { unlink(it) }
                return
            } else {
                node = node?.next
            }
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    private fun getNode(index: Int): Node {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index < size / 2) {
            var node = first
            repeat(index) {
                node = node?.next
            }
            return node!!
        } else {
            var node = last
            repeat(size - index - 1) {
                node = node?.prev
            }
            return node!!
        }
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

    private fun unlink(node: Node) {
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null) {
            last = before
        }
        if (before == null) {
            first = after
        }
        size--
    }
}