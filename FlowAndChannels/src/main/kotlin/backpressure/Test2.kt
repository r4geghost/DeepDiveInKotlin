package backpressure

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    // producer с буфером
    val flow = flow {
        repeat(100) {
            println("Emitted: $it")
            emit(it)
            println("After emit: $it")
            delay(100)
        }
    }.buffer( // создаем буфер
        capacity = 5, // можно также указывать его размер (по умолчанию BUFFERED = 64 элемента)
        onBufferOverflow = BufferOverflow.DROP_OLDEST
        /* onBufferOverflow - стратегия при переполнении буфера (по умолчанию SUSPEND)
            SUSPEND — producer, который отправляет значение, приостанавливается, пока буфер полон.
            DROP_OLDEST — при переполнении из буфера удаляется самое старое значение, а новое добавляется, всё это без приостановки.
            DROP_LATEST — при переполнении буфер остаётся без изменений, а значение, которое пытались добавить, отбрасывается, также без приостановки.
        */
    )

    scope.launch {
        flow.collect {
            println("Collected: $it")
            delay(1000)
            println(it)
        }
    }
}