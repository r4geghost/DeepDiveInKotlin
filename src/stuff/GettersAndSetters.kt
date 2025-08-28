package stuff

enum class LogLevel {
    TRACE, DEBUG, INFO, WARN, ERROR, FATAL
}

class Config {
    var timeout: Int = 30
        set(value) {
            if (value !in 1..300) {
                println("Ошибка: Время ожидания должно быть в диапазоне от 1 до 300 секунд. Установлено значение по умолчанию.")
                field = 30
            } else {
                field = value
            }
        }

    var maxRetries: Int = 3
        set(value) {
            if (value < 0) {
                println("Ошибка: Максимальное количество повторных попыток не может быть отрицательным. Установлено значение по умолчанию.")
                field = 3
            } else {
                field = value
            }
        }

    var loggingLevel: LogLevel = LogLevel.INFO
        set(value) {
            if (value == LogLevel.TRACE || value == LogLevel.FATAL) {
                println("Ошибка: Уровень $value недоступен.")
            } else {
                field = value
            }
        }
    val isDebugMode: Boolean
        get() = loggingLevel == LogLevel.DEBUG

    val isProductionMode: Boolean
        get() = loggingLevel == LogLevel.ERROR

    fun printConfig() {
        println(
            "Время ожидания: $timeout секунд\n" +
                    "Максимальное количество повторных попыток: $maxRetries\n" +
                    "Уровень логирования: $loggingLevel\n" +
                    "Режим отладки: $isDebugMode\n" +
                    "Режим продакшн: $isProductionMode"
        )
    }
}