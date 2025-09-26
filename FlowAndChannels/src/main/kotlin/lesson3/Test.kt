package lesson3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.Executors

private var lastIndex = 0

private suspend fun loadVideos(): List<String> {
    delay(2000)
    return (lastIndex until (lastIndex + 10))
        .map { "Video $it" }
        .also {
            lastIndex += 10
            println("Loaded: ${it.joinToString()}")
        }
}

private suspend fun scroll(videos: List<String>) {
    delay(videos.size * 100L)
    println("User scrolled: ${videos.joinToString()}")
}

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val coroutineName = CoroutineName("MyCoroutine")
private val parent = Job()
private val exceptionHandler = CoroutineExceptionHandler { _, _ -> println("Handle exception") }
private val scope = CoroutineScope(dispatcher + coroutineName + parent + exceptionHandler)

fun main() {
    scope.launch {
        // flow - асинхронный поток данных (логика почти аналогична sequence)
        // flow позволяет использовать из корутин и внутри вызывать suspend функции
        flow<List<String>> {
            repeat(10) {
                val nextData = loadVideos() // загружаем данные
                emit(nextData) // кладем загруженные данные в поток (emit = yield в sequence)
            }
        }.collect { scroll(it) } // каждый раз, когда данные будут загружены, пользователь их скроллит
        // collect = forEach в sequence
        // все терминальные операторы в flow = suspend функции!
    }
    // другие способы создания flow
    val numbers = listOf(1, 2, 3, 4, 5).asFlow()
    val numbers2 = flowOf(1, 2, 3, 4, 5)
}