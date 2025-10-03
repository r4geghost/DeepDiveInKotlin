package delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val homework = Homework()

    homework.taskCount = 5
    homework.taskCount = 9

    homework.taskName = "new task"
    homework.taskName = "second task"
}

class Homework {
    var taskCount: Int by myObservable(0) { oldValue, newValue ->
        println("old task count: $oldValue")
        println("new task count: $newValue")
    }

    var taskName: String by myObservable("") { oldValue, newValue ->
        println("old task name: $oldValue")
        println("new task name: $newValue")
    }
}

fun <T> myObservable(initValue: T, callback: (oldValue: T, newValue: T) -> Unit): MyObservableProperty<T> {
    return MyObservableProperty(initValue, callback)
}

class MyObservableProperty<T>(
    initValue: T,
    private val callback: (oldValue: T, newValue: T) -> Unit
) : ReadWriteProperty<Any, T> {

    private var currentValue = initValue

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return currentValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val oldValue = currentValue
        currentValue = value
        callback(oldValue, value)
    }
}