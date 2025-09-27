package hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

object Repository2 {

    // горячий флоу
//    val timer = MutableSharedFlow<Int>()
//        .apply {
//            scope.launch {
//                // подписываемся на холодный флоу
//                getTimerFlow().collect {
//                    emit(it)
//                }
//            }
//        }
//        .asSharedFlow()

    // аналогично через функцию shareIn
    val timer = getTimerFlow().shareIn(
        scope,
        SharingStarted.Eagerly // когда нужно начинать эмитить данные
        // Eagerly - как у горячих флоу, т.е. сразу при создании объекта, независимо есть ли подписчики
        // Lazily - как у холодных флоу, т.е. при первом подписчике, но эмиты будут всегда
        // WhileSubscribed - аналогично Lazily, но закрывается если нет подписчиков
    )

    // холодный флоу
    fun getTimerFlow(): Flow<Int> {
        return flow {
            var seconds = 0
            while (true) {
                emit(seconds++)
                delay(1000)
            }
        }
    }
}