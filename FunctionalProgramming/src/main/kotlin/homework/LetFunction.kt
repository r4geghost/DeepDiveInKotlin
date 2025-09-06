package homework

/*
    Реализуйте функцию, которая принимает объект типа MyUser с нулабельным свойством email.
    Если email не равен null, функция должна вызвать метод отправки сообщения (sendEmail),
    передавая этот email как параметр. Для проверки и вызова используйте функцию let.
*/

/**
 * Класс для представления пользователя.
 */
data class MyUser(val name: String, val email: String?)

/**
 * Отправляет сообщение на указанный email.
 * Реализация метода дана для примера.
 */
fun sendEmail(email: String) {
    println("Сообщение отправлено на $email")
}

/**
 * Проверяет email пользователя и вызывает sendEmail, если email не null.
 *
 * @param user объект пользователя с возможным отсутствующим email.
 */
fun processUserEmail(user: MyUser) {
    // Реализуйте логику обработки email с использованием функции let
    user.email?.let { sendEmail(it) }
}