package observer

// функциональный интерфейс - должен иметь один метод, реализацию которого можно передавать как лямбду
fun interface Observer<T> {
    fun onChanged(newValue: T)
}