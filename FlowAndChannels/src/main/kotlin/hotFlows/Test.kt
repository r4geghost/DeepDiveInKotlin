package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val timer = Repository2.timer
    // оба коллектора получают одни и те же эмиты!
    scope.launch {
        timer.collect {
            println("Coroutine 1: $it")
        }
    }
    scope.launch {
        delay(5000)
        timer.collect {
            println("Coroutine 2: $it")
        }
    }
}