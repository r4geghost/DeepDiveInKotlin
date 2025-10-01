package generics.reifiedAndStarProjection

import collections.MyArrayList
import collections.MyList
import collections.myListOf

fun main() {
    val workers = myListOf(
        Director("John"),
        Programmer("Nick"),
        Programmer("Max")
    )

    workers.myFilterIsInstance<Programmer>() // заменяет filter + map в нужный тип
        .forEach { it.writeCode() }
}

// свой вариант filterIsInstance
// reified R - информация о типе R не будет стираться, тип данных будет известен в runtime
// компилятор возьмет тело этой функции и вставит вместо вызова (inline)
// MyList<*> - звездная проекция (star projection)
// если элементом коллекции может быть любой тип, и в методе он не используется, пишем звездочку
inline fun <reified R> MyList<*>.myFilterIsInstance(): MyList<R> {
    val result = MyArrayList<R>()
    for (element in this) {
        if (element is R) {
            result.add(element as R)
        }
    }
    return result
}

open class Worker(val name: String)

class Programmer(name: String) : Worker(name) {
    fun writeCode() {
        println("Writing code...")
    }
}

class Director(name: String) : Worker(name)