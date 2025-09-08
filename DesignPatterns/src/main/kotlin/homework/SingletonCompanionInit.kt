package homework

/*
    Ваша задача – рефакторинг кода с применением паттерна Singleton, чтобы гарантировать существование
    только одного экземпляра SettingsManager на протяжении всего времени работы приложения.

    Требования:
        Реализовать паттерн Singleton – гарантировать наличие только одного экземпляра класса SettingsManager.
        Запретить создание извне экземпляра SettingsManager через вызов конструктора. Конструктор класса должен быть закрытым (private).
*/

// Ваша задача — сделать этот класс Singleton
class SettingsManager(context: Context) : BaseManager(context) {

    private val settings: MutableMap<String, String> = mutableMapOf()

    init {
        settings.putAll(context.defaultSettings)
    }

    fun getSetting(key: String): String? {
        return settings[key]
    }
}

open class BaseManager(val context: Context)

data class Context(val name: String, val defaultSettings: Map<String, String>)

fun main() {

}