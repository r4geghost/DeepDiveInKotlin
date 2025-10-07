package generics

fun main() {
    val programmer = Container(Programmer("John"))
    val director = Container(Director("Max"))
    val worker = Container<Worker>()

    copy(programmer, worker)

    println(programmer.value)
    println(director.value)
    println(worker.value)

    val a = Container(10)
    val b = Container("Hello")
    val c = Container<Any>() // в эту переменную можно положить объект любого другого класса (Any - родитель для всех)
    copy(a, c)
    copy(b, c)
}

/*
    Container<out T> (ковариантность)
    ├─ Передать: Container<T> или Container<РОДИТЕЛЬ T>
    ├─ PRODUCER: можем ЧИТАТЬ, нельзя ПИСАТЬ
    └─ Пример: List<out Animal> может быть List<Any>

    Container<in T> (контравариантность)
    ├─ Передать: Container<T> или Container<НАСЛЕДНИК T>
    ├─ CONSUMER: можем ПИСАТЬ, нельзя ЧИТАТЬ
    └─ Пример: Comparable<in String> может быть Comparable<CharSequence>

    Container<T> (инвариантность)
    ├─ Передать: только Container<T>
    └─ Можно и читать, и писать
 */
private fun <T> copy(src: Container<out T>, dst: Container<in T>) {
    dst.value = src.value
}

data class Container<T>(var value: T? = null)