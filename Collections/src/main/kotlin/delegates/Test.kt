@file:OptIn(ExperimentalEncodingApi::class)

package delegates

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.Delegates.observable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

    var password: String by encrypted() // объявляем делегат
    var creditCard: String by encrypted()
    // когда нужно будет вызвать getter/setter у переменных выше, будет вызван нужный метод из класса EncryptedProperty
}

// класс-делегат, который шифрует/дешифрует значения
class EncryptedProperty : ReadWriteProperty<Any, String> {
    // если нужно только получать значения, то можно использовать ReadOnlyProperty

    private var encryptedValue: String = ""

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("Encoded: $encryptedValue")
        val decoded = String(Base64.decode(encryptedValue.toByteArray())) // дешифруем в Base64
        println("Decoded: $decoded")
        return decoded
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("New value: $value")
        val encoded = Base64.encode(value.toByteArray()) // шифруем в Base64
        println("Encoded: $encoded")
        encryptedValue = encoded
    }
}

// функция, которая возвращает экземпляр делегата
fun encrypted() = EncryptedProperty()

