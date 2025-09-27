package coldFlows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object Repository {

    /* Холодные потоки данных

       Особенности:
       - не будет эмитить данные, пока на него никто не подписался (нет терминального оператора)
       - когда коллектору данные больше не нужны, флоу завершает свою работу
        (например, при получении нужного кол-ва элементов)
       - когда флоу перестанет эмитить данные, то метод collect будет завершен
       - при каждой подписке создаются новый поток данных (каждый коллектор получает свой флоу)
       - эмитить значения можно только из flow builder (извне нельзя)
    */
    val timer = getTimerFlow()

    private fun getTimerFlow(): Flow<Int> {
        // функция flow {...} создает холодный поток данных
        return flow {
            var seconds = 0
            while (true) {
                emit(seconds++)
                delay(1000)
            }
        }
    }
}