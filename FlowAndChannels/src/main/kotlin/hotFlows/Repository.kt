package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

object Repository {

    /* Горячие потоки данных

       Особенности:
       - эмитит данные независимо от наличия подписчиков (терминальных операторов)
       - все подписчики получают одни и те же элементы
       - если подписчикам не нужны данные, флоу продолжает работать (не завершается)
       - если в потоке больше нет данных, флоу продолжает работать (т.е. никогда не завершится)
    */
    private val _timer = MutableSharedFlow<Int>() // это не конструктор, а функция!
    val timer = _timer.asSharedFlow()

    init {
        // эмитить в горячий поток можно откуда угодно, например в инит блоке через корутину
        scope.launch {
            var seconds = 0
            while (true) {
                println("Emitted: $seconds")
                _timer.emit(seconds++)
                delay(1000)
            }
        }
    }
}