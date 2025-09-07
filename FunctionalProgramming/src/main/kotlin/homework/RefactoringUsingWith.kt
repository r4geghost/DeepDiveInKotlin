package homework

/*
    Функция formatText успешно решает свою задачу: формирует текстовый блок с заголовком, телом и футером,
    а также выполняет дополнительные операции, такие как удаление отладочных строк и ограничение длины текста.
    На первый взгляд, может показаться, что задача завершена. Однако профессиональная разработка на этом не заканчивается.

    В мире программирования важен не только результат, но и способ его достижения.
    Код, который вы пишете, читают другие разработчики, а также вы сами спустя некоторое время.

    Поэтому стоит стремиться к тому, чтобы код был:
        1) читаемым — понятным даже без подробных комментариев,
        2) поддерживаемым — легко модифицируемым и адаптируемым под новые задачи,
        3) компактным — без избыточных повторений или неочевидных решений.

    Метод formatText, который вы видите, работает корректно, но содержит множество повторяющихся вызовов объекта StringBuilder.
    Это делает код избыточным и менее удобным для чтения. Улучшить это можно с помощью функции with.

    Перепишите функцию formatText, применив функцию with для работы с объектом StringBuilder.
    Сохраните всю текущую функциональность, но сделайте код более компактным, читабельным и понятным.
*/

fun formatText(title: String, body: List<String>, footer: String): String {
    require(title.isNotBlank()) { "Title must not be blank" }
    require(body.isNotEmpty()) { "Body must contain at least one paragraph" }
    require(footer.isNotBlank()) { "Footer must not be blank" }

    val debugKeyword = "debug"
    val oldHeader = "=== Начало текста ==="
    val newHeader = "=== Новый заголовок ==="
    val maxTextLength = 500

    return with(StringBuilder()) {
        append("=== $title ===\n")
        body.forEach { paragraph -> append("$paragraph\n") }

        append("--- $footer ---\n")
        insert(0, "\n$oldHeader\n")

        append("\n=== Конец текста ===")

        indexOf(debugKeyword).takeIf { it != -1 }?.let {
            delete(it, it + debugKeyword.length)
        }

        indexOf(oldHeader).takeIf { it != -1 }?.let {
            replace(it, it + oldHeader.length, newHeader)
        }

        length.takeIf { it > maxTextLength }?.let {
            setLength(maxTextLength)
        }

        toString()
    }
}

fun main() {
    println(formatText("title", listOf("hello", "world"), "footer"))
}