package users

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observer.MutableObservable
import observer.Observable
import java.io.File

// class UserRepository private constructor () - приватный конструктор
class UserRepository private constructor() {

    // порядок объявления важен! - если есть init, сначала вызвать делать его
    init {
        println("Creating repository...")
    }

    private val file = File("DesignPatterns/users.json")

    // уже не backing field, а просто приватная переменная
    private val userList: MutableList<User> = loadUsers()

    private val _users: MutableObservable<List<User>> = MutableObservable(userList.toList())
    val users: Observable<List<User>>
        get() = _users as Observable<List<User>>

    private val _oldestUser: MutableObservable<User> = MutableObservable(userList.maxBy { it.age })
    val oldestUser: Observable<User>
        get() = _oldestUser as Observable<User>

    fun addUser(firstName: String, lastName: String, age: Int) {
        Thread.sleep(10_000) // искусственная задержка
        val newUser = User(userList.maxOf { it.id } + 1, firstName, lastName, age)
        userList.add(newUser)
        _users.currentValue = userList.toList()
        if (age > oldestUser.currentValue.age) {
            _oldestUser.currentValue = newUser
        }
    }

    fun removeUser(userId: Int) {
        Thread.sleep(10_000) // искусственная задержка
        userList.removeIf { it.id == userId }
        _users.currentValue = userList.toList()
        val newOldestUser = userList.maxBy { it.age }
        if (newOldestUser != oldestUser.currentValue) {
            _oldestUser.currentValue = newOldestUser
        }
    }

    fun saveChanges() {
        file.writeText(Json.encodeToString(userList))
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