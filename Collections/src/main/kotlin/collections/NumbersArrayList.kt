package collections

class NumbersArrayList : NumbersMutableList {

    private var numbers = arrayOfNulls<Int>(10)

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        growIfNeeded()
        numbers[size] = number
        size++
    }

    override fun add(index: Int, number: Int) {
        growIfNeeded()
        for (i in size downTo index + 1) {
            numbers[i] = numbers[i - 1]
        }
        numbers[index] = number
        size++
    }

    override fun removeAt(index: Int) {
        for (i in index until size - 1) {
            numbers[i] = numbers[i + 1]
        }
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
        return numbers[index]!! // не финальное решение
    }

    override fun clear() {
        numbers = arrayOfNulls(10)
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
            for (i in numbers.indices) {
                newArray[i] = numbers[i]
            }
            numbers = newArray
        }
    }
}