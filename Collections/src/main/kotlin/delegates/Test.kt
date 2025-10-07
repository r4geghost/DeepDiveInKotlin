@file:OptIn(ExperimentalEncodingApi::class)

package delegates

import meekie.properties.encrypted
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.Delegates.observable

fun main() {
    val user = User()
    user.password = "123456"
    println(user.password)
    user.creditCard = "1234 5678 9000 1111"
    println(user.creditCard)
    user.observable = 10 // отработает callback
    user.observable = 11 // отработает callback
}

// класс, который хранит данные пользователя
class User {
    val lazy by lazy {
        // встроенный делегат Kotlin (ленивая инициализация)
        "lazy"
    }

    var observable by observable(0) { _, oldValue, newValue ->
        // встроенный делегат Kotlin (паттерн наблюдатель)
        println("oldValue: $oldValue")
        println("newValue: $newValue")
    }

    var password: String by encrypted() // объявляем делегат (через функцию)
    var creditCard: String by encrypted()
    // когда нужно будет вызвать getter/setter у переменных выше, будет вызван нужный метод из класса EncryptedProperty
}

