package multithreading

import kotlin.concurrent.thread

fun main() {
    Thread {
        repeat(100_000) {
            print(" 0 ")
        }
    }.start()

    // kotlin.concurrent.thread - не нужно вызывать явно .start()
    thread {
        repeat(100_000) {
            print(" 1 ")
            // остановить поток на 100 миллисекунд
            Thread.sleep(100)
        }
    }

    repeat(100_000) {
        print(" * ")
    }
}