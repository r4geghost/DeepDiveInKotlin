package jobsHierarchy

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.concurrent.thread

private val scope = CoroutineScope(Dispatchers.Default)

fun main() {
    // считается один из основных потоков
//    thread {
//        repeat(3) {
//            println(it)
//            Thread.sleep(1000)
//        }
//    }

    // поток-демон (завершает свою работу, когда завершаются все основные потоки приложений)
    // если все потоки демоны - то они завершаться очень быстро
//    thread(isDaemon = true) {
//        while (true) {
//            println("...")
//            Thread.sleep(1000)
//        }
//    }

    // так все основные диспатчеры используют потоки демоны, корутина завершилась
//    scope.launch {
//        printNumber(1)
//    }

    // одно из решений - runBlocking - блокирует поток пока не завершится корутина - используется только в тестах!
//    runBlocking {
//        printNumber(1)
//    }

    // другое решение - свой диспатчер
//    val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
//    val newScope = CoroutineScope(dispatcher)
//    newScope.launch {
//        printNumber(2)
//    }

    // запуск одной корутины внутри другой - вторая корутина не запуститься, т.к. будет ожидание завершения suspend функции
//    scope.launch {
//        printNumber(1)
//        launch {
//            printNumber(2)
//        }
//    }

    val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    val newScope = CoroutineScope(dispatcher)
    // одновременный запуск двух корутин (внутри третьей)
    val job = newScope.launch {
        println("Job 0: ${coroutineContext.job}")
        println("Job 0 parent: ${coroutineContext.job.parent}")
        launch {
            println("Job 1 parent: ${coroutineContext.job.parent}")
            printNumber(1)
        }
        launch {
            println("Job 2 parent: ${coroutineContext.job.parent}")
            printNumber(2)
        }
    }
    Thread.sleep(3000)
    job.cancel()

    // можно создать родителя и передать в скоуп
    val parent = Job()
    val jobScope = CoroutineScope(parent)
    jobScope.cancel()
}

private suspend fun printNumber(number: Number) {
    while (true) {
        println(number)
        delay(1000)
    }
}