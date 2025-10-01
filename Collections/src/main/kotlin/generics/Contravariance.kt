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
    Container<in T> означает, что передать мы можем либо Container<T>, либо Container<Родитель типа T>
    = контравариантность (producer - можно считывать данные, но изменять нельзя)

    Container<out T> означает, что передать мы можем либо Container<T>, либо Container<Наследник типа T>
    = ковариантность (consumer - записывать данные можно, а считывать нельзя)

    Container<T> означает, что передать мы можем только Container<T>
    = инвариантность
 */
private fun <T> copy(src: Container<out T>, dst: Container<in T>) {
    dst.value = src.value
}

data class Container<T>(var value: T? = null)