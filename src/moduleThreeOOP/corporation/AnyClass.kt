package moduleThreeOOP.corporation

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val log1 = LogEvent("User logged in")
    val log2 = LogEvent(404)
    val log3 = LogEvent(UserAction.CLICK_BUTTON)
    println(log1)   // [2024-10-31 10:15:00]: User logged in
    println(log2)   // [2024-10-31 10:20:00]: 404
    println(log3)   // [2024-10-31 10:25:00]: UserAction: CLICK_BUTTON
}


// Класс для логирования событий
class LogEvent(
    private val eventData: Any
) {
    private val date: String = getCurrentDate()

    // вам понадобится этот метод чтобы получить текущую дату и время
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(Date())
    }

    override fun toString(): String {
        return "[$date]: $eventData"
    }
}

// Перечисление действий пользователя
enum class UserAction {
    LOGIN, LOGOUT, CLICK_BUTTON, VIEW_PAGE;

    override fun toString(): String {
        return "UserAction: $name"
    }
}