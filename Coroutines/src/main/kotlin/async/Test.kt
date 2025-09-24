package async

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + Job() + CoroutineName("My Coroutine"))

fun main() {
    // приложение не упадет, пока не распакуется объект Deferred
    // но дочерние корутины будут отменены в любом случае!
    val differed = scope.async {
        method()
    }
    // упадем здесь
    scope.launch {
        differed.await()
    }

    // но в случае ниже приложение упадет сразу, т.к. объект Deferred придет в родительскую корутину,
    // которая запущена корутин билдером launch, а он просто распакует объект Deferred и бросится исключение
    // (если у scope нет exception handler)
    scope.launch {
        async {
            method()
        }
    }
}

suspend fun method() {
    delay(3000)
    error("")
}

suspend fun method2() {
    delay(5000)
    error("Method2 is finished")
}