package homework

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Класс User, который необходимо сериализовать и десериализовать
@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val registeredAt: String
)

// Реализуйте эту функцию для сериализации объекта User
fun serializeUser(user: User): String {
    return Json.encodeToString(user)
}

// Реализуйте эту функцию для десериализации строки JSON в объект User
fun deserializeUser(json: String): User {
    return Json.decodeFromString<User>(json)
}