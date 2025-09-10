package users

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.Observer
import java.io.File

// class UserRepository private constructor () - приватный конструктор
class UserRepository private constructor() {

    // порядок объявления важен! - если есть init, сначала вызвать делать его
    init {
        println("Creating repository...")
    }

    private val file = File("DesignPatterns/users.json")

    private val observers: MutableList<Observer<List<User>>> = mutableListOf()

    fun addObserver(observer: Observer<List<User>>) {
        observers.add(observer)
        observer.onChanged(users)
    }

    private fun notifyObservers() {
        observers.forEach { it.onChanged(users) }
    }

    private val _users: MutableList<User> = loadUsers()
    val users
        get() = _users.toList()

    fun addUser(firstName: String, lastName: String, age: Int) {
        _users.add(User(users.maxOf { it.id } + 1, firstName, lastName, age))
        notifyObservers()
    }

    fun removeUser(userId: Int) {
        _users.removeIf { it.id == userId }
        notifyObservers()
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(_users))
    }

    private fun loadUsers(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    // Singleton финальная правильная реализация с критической секцией (double check)
    companion object { // аналог static в Java
        private val lock = Any()
        private var instance: UserRepository? = null

        fun getInstance(password: String): UserRepository {
            val correctPassword = File("DesignPatterns/password.txt").readText().trim()
            if (password != correctPassword) {
                throw IllegalArgumentException("Wrong password")
            }
            // если объект проинициализирован, вернем без захода в критическую секцию - нет очереди
            instance?.let { return it }
            // критическая секция
            synchronized(lock) {
                return instance ?: UserRepository().also { instance = it }
            }
        }
    }

    /*
        // Singleton

        companion object { // аналог static в Java
            private var instance: UserRepository? = null

            fun getInstance(password: String): UserRepository {
                val correctPassword = File("DesignPatterns/password.txt").readText().trim()
                if (password != correctPassword) {
                    throw IllegalArgumentException("Wrong password")
                } else {
                    return instance ?: UserRepository().also { instance = it }
                }
            }
        }
     */
    /*
        // Singleton lateinit
        companion object {
            private lateinit var instance: UserRepository

            fun getInstance(password: String): UserRepository {
                val correctPassword = File("DesignPatterns/password.txt").readText().trim()
                if (password != correctPassword) {
                    throw IllegalArgumentException("Wrong password")
                }
                if (!::instance.isInitialized) {
                    instance = UserRepository()
                }
                return instance
            }
        }
     */
    /*
        // Singleton init
        companion object {
            private val instance = UserRepository()

            fun getInstance(password: String): UserRepository {
                val correctPassword = File("DesignPatterns/password.txt").readText().trim()
                if (password != correctPassword) {
                    throw IllegalArgumentException("Wrong password")
                }
                return instance
            }
        }
     */
    /*
        // Singleton delegate ('by lazy') - создает экземпляр класса при первом обращении
        companion object {
            private val instance by lazy { UserRepository() }

            fun getInstance(password: String): UserRepository {
                val correctPassword = File("DesignPatterns/password.txt").readText().trim()
                if (password != correctPassword) {
                    throw IllegalArgumentException("Wrong password")
                }
                return instance
            }
        }
     */
}