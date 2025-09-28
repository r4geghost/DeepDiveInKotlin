package backpressure

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    // flow, который эмитит данные = producer
    val flow = flow {
        repeat(100) {
            println("Emitted: $it")
            emit(it) // emit - suspend функция, которая по умолчанию останавливается, пока consumer не обработает значение
            println("After emit: $it")
            delay(100)
        }
    }
    // корутина, которая подписывается на обновление flow = consumer
    scope.launch {
        flow.collect {
            println("Collected: $it")
            delay(1000)
            println(it)
        }
    }
    // выше пример backpressure - когда producer производит данные, чем consumer успевает их обработать
}