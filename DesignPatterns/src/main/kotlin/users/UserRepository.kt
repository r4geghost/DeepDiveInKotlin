package users

import kotlinx.serialization.json.Json
import java.io.File

// class UserRepository private constructor (password: String) - приватный конструктор
class UserRepository private constructor(password: String) {

    // порядок объявления важен! - если есть init, сначала вызвать делать его
    init {
        println("Creating repository...")
    }

    private val file = File("DesignPatterns/users.json")

    private val _user: MutableList<User> = loadUsers()
    val users
        get() = _user.toList()

    private fun loadUsers(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    // Singleton
    companion object { // аналог static в Java
        private var instance: UserRepository? = null

        fun getInstance(password: String): UserRepository {
            val correctPassword = File("DesignPatterns/password.txt").readText().trim()
            if (password != correctPassword) {
                throw IllegalArgumentException("Wrong password")
            } else {
                return instance ?: UserRepository(password).also { instance = it }
            }
        }
    }
}