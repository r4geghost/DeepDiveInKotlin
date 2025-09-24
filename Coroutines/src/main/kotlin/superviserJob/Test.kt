package superviserJob

import kotlinx.coroutines.*
import java.util.concurrent.Executors

// специальный тип SupervisorJob - наследники могут завершаться с ошибкой независимо друг от друга
// Children of a supervisor job can fail independently of each other.
private val parentJob = SupervisorJob()
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Caught exception!")
}
private val scope = CoroutineScope(dispatcher + parentJob + exceptionHandler)

fun main() {
    scope.launch {
        longOperation(3000, 1)
        error("") // поймаем исключение, но другие корутины не будут отменены!
    }
    scope.launch {
        longOperation(4000, 2)
    }
}

private suspend fun longOperation(timeMillis: Long, number: Int) {
    println("Started: $number")
    delay(timeMillis)
    println("Ended: $number")
}