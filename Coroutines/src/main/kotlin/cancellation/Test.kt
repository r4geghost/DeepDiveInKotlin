package cancellation

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + CoroutineName("test") + Job())

fun main() {
    val job: Job = scope.launch {
        timer()
    }
    Thread.sleep(3000)
    // метод cancel не отменяет корутину, а выставляет у контекста isActive = false
    // обработка этого флага = задача того, кто создает suspend функцию
    job.cancel()
}

// если вы создаете свою suspend функцию, то она должна поддерживать механизм отмены корутин!
private suspend fun timer() {
    withContext(dispatcher) {
        var seconds = 0
        while (true) {
            try {
                println("Hello from coroutine!")

                // нужно явно бросить исключение CancellationException
                // он не приводит к падению программы и его не нужно обрабатывать
//                if (!coroutineContext.isActive) throw CancellationException()

                // аналогичная работа под капотом у функции ensureActive
                ensureActive()

                // выводим только при isActive = true
                println("${seconds++} seconds")
                Thread.sleep(1000)
            } catch (e: CancellationException) {
                // обязательно пробрасывать CancellationException дальше!
                throw e
            } catch (e: Exception) {
                println("Caught $e")
            }
        }
    }
}

