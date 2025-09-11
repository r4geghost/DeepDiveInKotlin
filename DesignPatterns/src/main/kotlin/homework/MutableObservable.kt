package homework

/*
    Наш разработчик Вася Пупкин (который явно прогуливал курс по Kotlin 🙃) написал крайне неэффективную систему
    мониторинга данных. Вместо того, чтобы грамотно подписываться на изменения, он сделал так, что классы
    UserMonitor, OrderMonitor и PriceMonitor постоянно долбят бедный DataRepository, проверяя, изменились ли данные.

    🚨 Что в итоге?
    ❌ Лишняя нагрузка на систему, ведь код крутится в бесконечных циклах.
    ❌ Плохая масштабируемость, потому что больше подписчиков = больше запросов.
    ❌ Разработчики плачут, читая этот код.
    Ваша миссия:

    Спасти код от безумия Васи Пупкина и применить паттерн "Наблюдатель" (Observer).
    Что нужно сделать:

    ✅ Репозиторий DataRepository должен хранить данные в виде наблюдаемых объектов (MutableObservable).
    ✅ Мониторы (UserMonitor, OrderMonitor, PriceMonitor) должны уйти в утиль.
    ✅ Подписка (Observer) должна выполняться в main() - без лишних циклов!
 */

import kotlin.concurrent.thread
import kotlin.math.round
import kotlin.random.Random

// Репозиторий данных
object DataRepository {
    var userData: String = "User_Initial"
    var orderData: Int = 100
    var priceData: Double = 99.99

    // Метод обновления данных
    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let { userData = it }
        newOrder?.let { orderData = it }
        newPrice?.let { priceData = round(it * 100) / 100 }
    }
}


// Мониторинг данных с периодическим опросом
class UserMonitor(private val repository: DataRepository) {
    init {
        thread {
            var lastValue = repository.userData
            while (true) {
                println("UserMonitor: запрос к DataRepository")
                if (repository.userData != lastValue) {
                    println("UserMonitor: Обнаружено изменение данных пользователя: ${repository.userData}")
                    lastValue = repository.userData
                }
                Thread.sleep(1000)
            }
        }
    }
}

class OrderMonitor(private val repository: DataRepository) {
    init {
        thread {
            var lastValue = repository.orderData
            while (true) {
                println("OrderMonitor: запрос к DataRepository")
                if (repository.orderData != lastValue) {
                    println("OrderMonitor: Обнаружено изменение данных заказа: ${repository.orderData}")
                    lastValue = repository.orderData
                }
                Thread.sleep(1000)
            }
        }
    }
}

class PriceMonitor(private val repository: DataRepository) {
    init {
        thread {
            var lastValue = repository.priceData
            while (true) {
                println("PriceMonitor: запрос к DataRepository")
                if (repository.priceData != lastValue) {
                    println("PriceMonitor: Обнаружено изменение цены: ${repository.priceData}")
                    lastValue = repository.priceData
                }
                Thread.sleep(1000)
            }
        }
    }
}

// Класс для автоматического обновления данных
class DataUpdater(private val repository: DataRepository) {
    init {
        thread {
            while (true) {
                when (Random.nextInt(3)) {
                    0 -> repository.updateData(newUser = "User_${Random.nextInt(1, 100)}")
                    1 -> repository.updateData(newOrder = Random.nextInt(100, 200))
                    2 -> repository.updateData(newPrice = Random.nextDouble(50.0, 150.0))
                }
                val millis = Random.nextLong(10000L)
                Thread.sleep(millis) // Симуляция времени между обновлениями
            }
        }
    }
}



fun main() {
    // Запуск обновления данных
    DataUpdater(DataRepository)

    // Запуск мониторинга
    UserMonitor(DataRepository)
    OrderMonitor(DataRepository)
    PriceMonitor(DataRepository)
}