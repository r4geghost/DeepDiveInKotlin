package collections

class NumbersArrayList : NumbersMutableList {

    companion object {
        private const val INITIAL_CAPACITY = 10 // const = не создается get
        // + значение будет встроено вместо их вызова (не сама переменная, а сразу значение)
        // + должны быть привязаны к классу
    }

    private var numbers = arrayOfNulls<Int>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        growIfNeeded()
        numbers[size] = number
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        growIfNeeded()
        System.arraycopy(numbers, index, numbers, index + 1, size - index)
        numbers[index] = number
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
        System.arraycopy(numbers, index + 1, numbers, index, size - index - 1)
        size--
        numbers[size] = null
    }

    override fun remove(number: Int) {
        for (i in numbers.indices) {
            if (numbers[i] == number) {
                removeAt(i)
                return
            }
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return numbers[index]!! // не финальное решение
    }

    override fun clear() {
        numbers = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        for (i in numbers.indices) {
            if (numbers[i] == number) {
                return true
            }
        }
        return false
    }

    private fun growIfNeeded() {
        if (numbers.size == size) {
            val newArray = arrayOfNulls<Int>(size * 2)
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