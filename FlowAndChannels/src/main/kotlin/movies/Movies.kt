package movies

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.time.measureTime

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    val job = scope.launch {
        val time = measureTime {
            loadMovies()
        }
        println(time)
    }
    scope.launch {
        delay(3100)
        job.cancel()
    }
}

private suspend fun loadMoviesIds(): List<Int> {
    delay(3000)
    return (0..100).toList()
}

private suspend fun loadMovieById(id: Int): String {
    delay(100)
    return "Movie: $id"
}

// внутри suspend функции не желательно обращаться к какому-то внешнему scope
// поэтому используем функцию coroutineScope {...} или supervisorScope {...} чтобы использовался supervisorJob
private suspend fun loadMovies(): List<String> {
    return coroutineScope {
        loadMoviesIds().map {
            async {
                loadMovieById(it).also { println(it) }
            }
        }.awaitAll()
    }
}