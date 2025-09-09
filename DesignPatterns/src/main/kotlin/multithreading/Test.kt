package multithreading

import kotlin.concurrent.thread

fun main() {
    val counter = Counter()

    val thread1 = thread {
        repeat(1_000_000) {
            counter.increment()
        }
    }

    val thread2 = thread {
        repeat(1_000_000) {
            counter.increment()
        }
    }

    thread1.join()
    thread2.join()
    println(counter.count)
}

class Counter {
    // мьютекс
    private val lock = Any()

    var count = 0

    fun increment() {
        // блок синхронизации
        synchronized(lock) {
            // критическая секция
            count++
        }
    }
}