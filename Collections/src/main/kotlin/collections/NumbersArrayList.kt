package collections

class NumbersArrayList : NumbersMutableList {

    private var numbers = arrayOfNulls<Int>(10)

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        if (numbers.size == size) {
            val newArray = arrayOfNulls<Int>(size * 2)
            for (i in numbers.indices) {
                newArray[i] = numbers[i]
            }
            numbers = newArray
        }
        numbers[size] = number
        size++
    }

    override fun removeAt(index: Int) {
        for (i in index until size - 1) {
            numbers[i] = numbers[i + 1]
        }
        size--
        numbers[size] = null
    }

    override fun get(index: Int): Int {
        return numbers[index]!! // не финальное решение
    }
}