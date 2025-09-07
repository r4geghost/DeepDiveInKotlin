package homework

/*
    Вам нужно реализовать обработку объекта NewUser с использованием функции run. Задача демонстрирует, почему
    использование run в данном случае может быть предпочтительным выбором среди других подходов: with, let, apply, also,
    а также простого способа через проверку на null.

    Ваша задача:
        1) Используя функцию run, напишите код, который для объекта NewUser:
            - Возвращает строку "NewUser {name} is active", если пользователь активен.
            - Возвращает строку "NewUser {name} is not active", если пользователь неактивен.
            - Возвращает null, если объект NewUser равен null.
        2) Сохраните результат в переменную runResult.

    Реализация с использованием других функций (with, let и обычной проверки на null) уже готова.
    Вам нужно дополнить их своей версией с использованием run.
*/

data class NewUser(var name: String, var isActive: Boolean)

fun main() {
    val user: NewUser? = NewUser("John Doe", true) // Nullable объект

    // Реализация без использования scope-функций
    val result = if (user != null) {
        if (user.isActive) "NewUser ${user.name} is active" else "NewUser ${user.name} is not active"
    } else {
        null
    }

    // Реализация с использованием with
    val withResult = if (user != null) {
        with(user) {
            if (isActive) "NewUser $name is active" else "NewUser $name is not active"
        }
    } else {
        null
    }

    // Реализация с использованием let
    val letResult = user?.let {
        if (it.isActive) "NewUser ${it.name} is active" else "NewUser ${it.name} is not active"
    }

    // Реализация с использованием run
    // TODO: Реализуйте обработку объекта user с использованием run
    val runResult =

    // Реализация с использованием apply
    // apply здесь не подходит, потому что:
    // 1. apply используется для настройки объекта (this) и возвращает его.
    // 2. Нам нужен результат вычисления в виде строки, а не сам объект NewUser.
    val applyResult = null // Не применимо в данном случае.

    // Реализация с использованием also
    // also здесь не подходит, потому что:
    // 1. also возвращает исходный объект, а не результат вычислений.
    // 2. Нам нужно вернуть строку, а не объект NewUser.
    val alsoResult = null // Не применимо в данном случае.

    // Вывод всех результатов
    println("result: $result")
    println("withResult: $withResult")
    println("letResult: $letResult")
    println("runResult: $runResult")
    println("applyResult: $applyResult")
    println("alsoResult: $alsoResult")
}