package exceptions

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        // оборачиваем код ВНУТРИ корутины в try/catch
        try {
            method()
        } catch (e: Exception) {
            println("Exception: $e")
        }

        // функциональный стиль (под капотом try/catch
        runCatching {
            method()
        }.onSuccess { // обработка при успехе
            println("Success")
        }.onFailure { // обработка при ошибке
            println("Failure: $it") // Failure: java.lang.IllegalStateException:
        }
    }

    // ВАЖНО: если в дочерней корутине было брошено исключение,
    // то оно передается наверх родительским объектам

    // обработка исключений в родительской корутине через exception handler
    val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        println("Exception handled")
    }
    val newScope = CoroutineScope(dispatcher + exceptionHandler)
    newScope.launch {
        method()
    }
}

suspend fun method() {
    delay(3000)
    error("") // один из способов бросить исключение
}