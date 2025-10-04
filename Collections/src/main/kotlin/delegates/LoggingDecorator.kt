package delegates

class LoggingMutableList<T>(private val mutableList: MutableList<T>): MutableList<T> by mutableList {
    override fun add(element: T): Boolean {
        return mutableList.add(element).also {
            if (it) {
                println("Element $element was added")
            } else {
                println("Element $element was not added")
            }
        }
    }
}

fun main() {
    val list = mutableListOf(1,2,3,4)
    val loggingMutableList = LoggingMutableList(list)
    loggingMutableList.add(5)
}