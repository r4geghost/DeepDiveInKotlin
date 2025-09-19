package collections

class MyArrayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    companion object {
        private const val INITIAL_CAPACITY = 10 // const = не создается get
        // + значение будет встроено вместо их вызова (не сама переменная, а сразу значение)
        // + должны быть привязаны к классу
    }

    private var numbers = arrayOfNulls<Any>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T) {
        growIfNeeded()
        numbers[size] = element
        size++
    }

    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        growIfNeeded()
        System.arraycopy(numbers, index, numbers, index + 1, size - index)
        numbers[index] = element
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
        System.arraycopy(numbers, index + 1, numbers, index, size - index - 1)
        size--
        numbers[size] = null
    }

    override fun remove(element: T) {
        for (i in numbers.indices) {
            if (numbers[i] == element) {
                removeAt(i)
                return
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return numbers[index] as T // downcast здесь будет всегда выполняться без ошибки
    }

    override fun clear() {
        numbers = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(element: T): Boolean {
        for (i in numbers.indices) {
            if (numbers[i] == element) {
                return true
            }
        }
        return false
    }

    private fun growIfNeeded() {
        if (numbers.size == size) {
            val newArray = arrayOfNulls<Any>(size * 2)
            System.arraycopy(numbers, 0, newArray, 0, size) // native - реализация на C++ под капотом
            numbers = newArray
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