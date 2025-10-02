package inline

fun main() {
    val list = (0..100).toList()
    list.myFilter(object : Condition<Int> {
        override fun isSuitable(item: Int): Boolean {
            return item % 2 == 0
        }
    }).forEach(::println)

    list.myFilter {
        if (it == 50) return // можно выйти из метода, если функция inline (Non local return)
        it % 2 == 0
    }.forEach(::println)
}

interface Condition<T> {
    fun isSuitable(item: T): Boolean
}

/* Проблемы реализации через анонимные классы:
    1) в каждый вызов функции создается новый объект анонимного класса
    2) нельзя никак выйти из метода (например, если обработали нужное кол-во элементов
    3) нельзя вызвать suspend функцию внутри (даже если элемент, у которого вызывается функция, объявлен в скоупе)
    4) узнать тип generic в runtime нельзя
 */
// без inline под капотом такая реализация (через анонимный класс)
fun <T> List<T>.myFilter(condition: Condition<T>): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (condition.isSuitable(item)) {
            result.add(item)
        }
    }
    return result
}

// inline подставляет тело функции вместо вызова!
// функция БУДЕТ СОЗДАНА, но никогда не будет вызвана - просто ее тело будет подставлено вместо вызова
/* Минусы inline:
    1) больший размер байт-кода (т.к. тело будет встраиваться во все места вызова)
    2) если функция ничего не принимает в качестве параметра, ее нет смысла делать inline
 */
inline fun <T> List<T>.myFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}
