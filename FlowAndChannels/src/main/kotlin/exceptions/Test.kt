package exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Exception handled in exceptionHandler: $throwable")
}

private val scope = CoroutineScope(dispatcher + exceptionHandler)

fun main() {
    val flow = getFlow()
    scope.launch {
        flow.collect {// collect выбрасывает исключение (во время обработки эмита)
            println(it)
        }
        // исключение поймает exceptionHandler
    }

    scope.launch {
        try {
            flow.collect {// collect выбрасывает исключение (во время обработки эмита)
                println(it)
            }
        } catch (e: Exception) { // исключение поймает try/catch
            println("Exception handled in try/catch: $e")
        }
    }

    scope.launch {
        runCatching {
            flow.collect {// collect выбрасывает исключение (во время обработки эмита)
                println(it)
            }
        }.onFailure { println("Exception handled in runCatching: $it") }  // исключение поймает runCatching
    }

    // наилучший способ обработки исключений в флоу = использование оператора catch
    scope.launch {
        flow.retry(1) {
            // если нужно, чтобы флоу перезапускался после ошибки (в качестве параметра передается кол-во попыток)
            // перезапуск будет с самого начала флоу!
            // обязательно перед блоком catch, иначе не выполнится!
            true // повторяем независимо от типа исключения
        }
            .catch {
                println("Exception handled in catch operator: $it")
            }
            .collect { println(it) }
    }
}

// оборачивать внутри создния флоу нельзя! это нарушает exception transparency!
// exception transparency = если исключение было брошено внутри флоу, то флоу обязательно должен завершиться!
// если исключение было выброшено внутри блок collect, то обработка будет внутри флоу, что не корректно!
private fun getFlow(): Flow<Int> = flow {
    repeat(5) {
        emit(it)
    }
    error("Exception in flow") // бросаем исключение внутри флоу
}