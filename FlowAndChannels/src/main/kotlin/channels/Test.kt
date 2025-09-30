package channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

// создаем канал
private val channel = Channel<Int>(
    capacity = 5, // RENDEZVOUS (буфер = 1, стратегия SUSPEND), CONFLATED (буфер = 1, стратегия DROP_OLDEST), UNLIMITED и BUFFERED
    onBufferOverflow = BufferOverflow.DROP_OLDEST
) {
    // функция, которая вызовется, если элемент был отправлен, но не получен
    println("Element $it was removed")
}

fun main() {
    // каналы очень похожи на BlockingQueue на Java
    scope.launch {
        repeat(100) {
            println("Sending $it")
            channel.send(1)
            println("Sent $it")
            delay(100)
        }
    }

    scope.launch {
        repeat(100) {
            println("Sending $it")
            channel.send(2)
            println("Sent $it")
            delay(100)
        }
    }

    scope.launch {
        delay(5000)
        // каналы можно закрывать!
        channel.close()
        // если попытаться положить данные в закрытый канал, будет брошено исключение ClosedSendChannelException
    }

    // каждый consumer получает свои данные (неодинаковые)
    // + по умолчанию стратегия suspend и буфер с 1 элементом
    scope.launch {
        channel.consumeEach {
            delay(1008)
            println("Consumer 1 received $it")
        }
    }
    scope.launch {
        channel.consumeEach {
            delay(1008)
            println("Consumer 2 received $it")
        }
    }

    scope.launch {
        // желательно отменять все потоки, которые были созданы executor service
        delay(10000)
        dispatcher.close()
    }
}