package observer

interface Observable<T> {

    val currentValue: T
    val observers: List<Observer<T>>

    fun registerObserver(observer: Observer<T>)

    fun unregisterObserver(observer: Observer<T>)

    fun notifyObservers() {
        observers.forEach { it.onChanged(currentValue) }
    }
}