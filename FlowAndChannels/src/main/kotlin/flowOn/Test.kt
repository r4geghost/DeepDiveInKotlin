package flowOn

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

// создаем dispatcher с одним потоком
private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        getFlow()
            .onStart { println("onStart: ${getCurrentThread()}") } // onStart {...} вызывается перед подпиской
            .onEach { println("onEach 1: ${getCurrentThread()}") }
            .flowOn(Dispatchers.Default) // все что до него (выше = upstream) выполниться на наш Dispatchers.Default
            .map {
                println("Map: ${getCurrentThread()}")
                it
            }
            .onEach { println("onEach 2: ${getCurrentThread()}") }
            .flowOn(Dispatchers.IO) // все что до него (выше) будет переключено на Dispatchers.IO
            .collect {
                println("collect : ${getCurrentThread()}") // выполниться на нашем dispatcher
            }
    }
}

private fun getFlow(): Flow<Int> = flow {
    var seconds = 0
    while (true) {
        println("Emitting $seconds on ${getCurrentThread()}")
        emit(seconds++)
        delay(1000)
    }
}

// тип данных = String! - для java кода, нужно явно указать, иначе если прилетит null, будет ошибка
private fun getCurrentThread(): String = Thread.currentThread().name
