package coldFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val timer = Repository.timer
    scope.launch {
        timer.collect {
            println("$it seconds")
        }
    }
    // чтобы было два разных коллектора, которые обрабатывают элементы флоу одновременно,
    // нужно использовать разные корутины (в рамках одной не сработает, т.к. collect - suspend функция
    scope.launch {
        timer.collect {
            println("$it seconds")
        }
    }
}