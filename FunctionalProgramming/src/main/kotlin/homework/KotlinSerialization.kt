package homework

// Класс User, который необходимо сериализовать и десериализовать
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val registeredAt: String
)

// Реализуйте эту функцию для сериализации объекта User
fun serializeUser(user: User): String {
    // Реализуйте сериализацию
    return ""
}

// Реализуйте эту функцию для десериализации строки JSON в объект User
fun deserializeUser(json: String): User {
    // Реализуйте десериализацию
    return User(0, "", "", "")
}