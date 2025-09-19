package collections

fun main() {
    val numbers = MyHashSet<Int>()
    repeat(99) {
        numbers.add(it)
    }
    numbers.forEach(::println)
}