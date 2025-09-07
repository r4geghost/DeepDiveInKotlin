package extentions

// inline функция = при вызове будет просто вызываться код из этой функции
// (без создания интерфейса и вызова метода интерфейса, выше производительность)
inline fun <T, R> Iterable<T>.transform(operation: (T) -> R): List<R> {
    val result = mutableListOf<R>()
    for (item in this) {
        result.add(operation(item))
    }
    return result
}

// синтаксис функций = "(тип на вход) -> тип на выход"
// функция высшего порядка = принимает другие функции в качестве параметра или возвращать их
inline fun <T> Iterable<T>.filter(isSuitable: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        if (isSuitable(item)) {
            result.add(item)
        }
    }
    return result
}

inline fun <T> List<T>.myForEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

inline fun <T, R> T.myLet(operation: (T) -> R): R = operation(this)

// передаваемая функция - для работы с объектом класса (принимает в качестве аргумента - объект класса T)
inline fun <T> T.myAlso(operation: (T) -> Unit): T {
    operation(this)
    return this
}

// передаваемая функция - extension на тип T, а значит внутри этой лямбды мы сможем работать, как будто внутри класса T
// Иначе - работает с методами класса
inline fun <T> T.myApply(operation: T.() -> Unit): T {
    operation()
    return this
}

// функция with != extension + возвращает результат выполнения лямбды
inline fun <T, R> myWith(item: T, operation: T.() -> R): R = operation(item)