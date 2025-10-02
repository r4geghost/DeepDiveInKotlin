package inline

fun main() {
    val user = User(UserId(0), "John")
    println(user)
    user.id.showValue()
}

// чтобы не ошибиться при передаче id, можно использовать отдельный класс (например, UserId)
// минусы - каждый раз создается объект класса UserId
// решение - value class - они же inline classes
data class User(val id: UserId, val name: String)

@JvmInline // обязательная аннотация
value class UserId(val value: Int) { // под капотом будет просто id типа Int

    fun showValue() { // в байт-коде этот метод станет статическим
        println(value)
    }
}
