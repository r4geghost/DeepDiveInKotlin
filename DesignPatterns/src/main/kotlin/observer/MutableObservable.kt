package observer

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
        notifyObservers()
    }

    override fun unregisterObserver(observer: Observer<T>) {
        _observers.remove(observer)
    }
}