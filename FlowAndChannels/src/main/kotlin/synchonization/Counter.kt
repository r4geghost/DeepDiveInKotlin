package synchonization

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

class Counter {

    // для синхронизации в корутинах используется объект Mutex
    private val mutex = Mutex()
    var value: Int = 0

    suspend fun increment() {
        // аналогично synchronized(lock), но конкретно класс Mutex
        mutex.withLock {
            // в этой секции одновременно может находиться только одна корутина
            // при этом поток не блокируется, а приостанавливает корутину, которая ждет освобождения секции
            delay(1)
            value++
        }

        /* также можно сделать так:
            try {
                mutex.lock()
                delay(1)
                value++
            } finally {
                // если вдруг произошла ошибка или все успешно, замок надо открыть
                mutex.unlock()
            }
        */
    }
}

fun main() {
    val counter = Counter()
    // функция buildList для заполнения списка внутри лямбды (на выходе - неизменяемый список)
    scope.launch {
        buildList {
            repeat(100) {
                launch {
                    repeat(10) {
                        counter.increment()
                    }
                }.let { add(it) }
            }
        }.joinAll()
        println(counter.value)
    }

}