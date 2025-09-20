package collections

fun <T> myListOf(vararg items: T) : MyList<T> {
    return ImmutableList(*items) // * преобразует массив в аргументы переменной длины
}

private class ImmutableList<T>(vararg elements: T): MyList<T> {

    private val array = elements

    override val size: Int
        get() = array.size

    override fun get(index: Int): T {
        return array[index]
    }

    override fun contains(element: T): Boolean {
        return array.contains(element)
    }

    override fun iterator(): Iterator<T> {
        return array.iterator()
    }

}