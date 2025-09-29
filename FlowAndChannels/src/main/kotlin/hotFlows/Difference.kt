package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.concurrent.thread

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

private val sharedFlow = MutableSharedFlow<Int>()
private val stateFlow = MutableStateFlow(0)

fun main() {
    emitFromCoroutine()

    stateFlow.value // можно получить текущее значение из state flow (из синхронного кода тоже!)

    sharedFlow.onEach {
        delay(1000)
        println("Shared Flow $it")
    }
        .launchIn(scope)    // во время вызова collect эмиты будут останавливаться до конца обработки элемента
                            // (т.к. BufferOverflow.SUSPEND и уже есть подписчик consumer!)
                            // но если бы подписка произошла позже запуска эмитов, ничего бы не было обработано

    stateFlow.onEach {
        delay(1000)
        println("State Flow $it")
    }
        .launchIn(scope)    // во время вызова collect эмиты не будут останавливаться,
                            // т.к. у MutableStateFlow BufferOverflow.DROP_OLDEST (обрабатываются только последние эмиты)

    emitFromStandardMethod()
}

// кладем данные из обычного метода
fun emitFromStandardMethod() {
    Thread.sleep(2000)
    thread {
        repeat(100) {
            sharedFlow.tryEmit(it) // пытается положить значение без прерывания,
            // но по умолчанию в sharedFlow все новые элементы будут выбрасываться
            // одно из решений - поставить большой буфер (replay) либо явно указать DROP_OLDEST
        }
    }
    thread {
        repeat(100) {
            stateFlow.tryEmit(it) // пытается положить значение без прерывания,
            // в stateFlow все работает по умолчанию (т.к. replay = 1 + DROP_OLDEST)
        }
    }
}

// кладем данные в корутине
fun emitFromCoroutine() {
    scope.launch {
        delay(2000)
        repeat(100) {
            sharedFlow.emit(it)
        }
    }
    scope.launch {
        delay(2000)
        repeat(100) {
            stateFlow.emit(it)
        }
    }
}