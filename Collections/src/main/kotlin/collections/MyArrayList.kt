package collections

class MyArrayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    companion object {
        private const val INITIAL_CAPACITY = 10 // const = не создается get
        // + значение будет встроено вместо их вызова (не сама переменная, а сразу значение)
        // + должны быть привязаны к классу
    }

    private var elements = arrayOfNulls<Any>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        growIfNeeded()
        elements[size] = element
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        growIfNeeded()
        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
    }

    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
    }

    override fun remove(element: T) {
        for (i in elements.indices) {
            if (elements[i] == element) {
                removeAt(i)
                return
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return elements[index] as T // downcast здесь будет всегда выполняться без ошибки
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {

            private var nextIndex = 0

            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            override fun next(): T {
                return elements[nextIndex++] as T
            }
        }
    }

    override fun contains(element: T): Boolean {
        for (i in elements.indices) {
            if (elements[i] == element) {
                return true
            }
        }
        return false
    }

    private fun growIfNeeded() {
        if (elements.size == size) {
            val newArray = arrayOfNulls<Any>(size * 2)
            System.arraycopy(elements, 0, newArray, 0, size) // native - реализация на C++ под капотом
            elements = newArray
        }
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