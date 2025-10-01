package generics

import collections.MyArrayList
import collections.MyList
import collections.MyMutableList

fun main() {
    val john = Programmer("John")
    showName(john)

    val helen = Programmer("Work")
    val workers: MyMutableList<Programmer> = MyArrayList<Programmer>().apply {
        add(john)
        add(helen)
    }

    showCount(workers) // будет возникать инвариантность без доработок (см. ниже)!

    /*
        Вариантность - это сохранение совместимости присваивания исходных типов у производных типов
        т.е. в переменную типа Worker мы сможем положить объект типа Programmer (здесь = исходные типы),
        однако в переменную производного типа (например, MyList<Worker>) мы не сможем положить MyList<Programmer>
        (коллекция Programmer не наследуется от коллекции Worker)

        Если мы хотим, чтобы производные типы сохранили совместимость присваивания,
        то при указании типа нужно добавить ключевое слово out (например, interface MyList<out T>)

        Такое поведение называется ковариантностью

        Если делать класс ковариантным, то он должен быть неизменяемым (почему - см. ниже)

        val elements: MyMutableList<Any> = workers // обращаемся к тому же объекту, не копия!
        elements.add("Hello")
        // мы сделали upcast, который все сломал!
        for (programmer in workers) {
            println(programmer.name)
            // java.lang.ClassCastException - даже компилятор не будет знать, что в списке лежит не объект класса Any
        }
     */
}

private fun showName(worker: Worker) {
    println(worker.name)
}

private fun showCount(workers: MyList<Worker>) {
    println(workers.size)
}

open class Worker(val name: String)

class Programmer(name: String) : Worker(name)