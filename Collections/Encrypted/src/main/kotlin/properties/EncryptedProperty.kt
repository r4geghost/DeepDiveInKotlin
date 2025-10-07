@file:OptIn(ExperimentalEncodingApi::class)

package meekie.properties

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// класс-делегат, который шифрует/дешифрует значения
// internal constructor() -> снаружи нельзя создать через конструктор
// в байт-коде будет public! (т.е. при обращении к этому классу из Java, он будет доступен в любом модуле
class EncryptedProperty internal constructor() : ReadWriteProperty<Any, String> {
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

// extension-функция, вызванная в Java, не будет работать - компилятор Java не умеет добавлять методы в существующие классы!
// но можно импортировать как статический метод, и передать параметр!
fun String.encode(): String {
    return Base64.encode(this.toByteArray())
}