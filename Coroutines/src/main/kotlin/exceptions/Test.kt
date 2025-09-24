package exceptions

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

private val scope = CoroutineScope(dispatcher)

fun main() {
    // в launch можно передавать любые составляющие CoroutineContext, в том числе exception handler (НО ТОЛЬКО ДЛЯ РОДИТЕЛЬСКОЙ КОРУТИНЫ, запускаемой у scope)
    scope.launch(CoroutineExceptionHandler { _, _ -> println("Catch exception!") }) {
        // оборачиваем код ВНУТРИ корутины в try/catch

        // в дочерние корутины нельзя передавать exception handler! (работать не будет)
        launch(Dispatchers.Default) {
            method()
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