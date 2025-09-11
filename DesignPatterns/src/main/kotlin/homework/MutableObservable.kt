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

    val userData: MutableObservable<String> = MutableObservable("User_Initial")
    val orderData: MutableObservable<Int> = MutableObservable(100)
    val priceData: MutableObservable<Double> = MutableObservable(99.99)

    // Метод обновления данных
    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let { userData.currentValue = it }
        newOrder?.let { orderData.currentValue = it }
        newPrice?.let { priceData.currentValue = round(it * 100) / 100 }
    }
}

fun interface Observer<T> {
    fun onChanged(value: T)
}

interface Observable<T> {
    val currentValue: T
    val observers: List<Observer<T>>

    fun registerObserver(observer: Observer<T>)

    fun unregisterObserver(observer: Observer<T>)
    fun notifyObservers() {
        observers.forEach { it.onChanged(currentValue) }
    }
}

class MutableObservable<T>(initValue: T) : Observable<T> {
    override var currentValue: T = initValue
        set(value) {
            field = value
            notifyObservers()
        }

    private val _observers = mutableListOf<Observer<T>>()
    override val observers: List<Observer<T>>
        get() = _observers.toList()

    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun unregisterObserver(observer: Observer<T>) {
        _observers.remove(observer)
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

    // Подписка на обновления данных пользователя
    DataRepository.userData.registerObserver { newValue ->
        println("UserMonitor: Обнаружено изменение данных пользователя: $newValue")
    }

    // Подписка на обновления данных заказов
    DataRepository.orderData.registerObserver { newValue ->
        println("OrderMonitor: Обнаружено изменение данных заказа: $newValue")
    }

    // Подписка на обновления данных цены
    DataRepository.priceData.registerObserver { newValue ->
        println("PriceMonitor: Обнаружено изменение цены: $newValue")
    }
}