package users

fun main() {
    UserRepository.getInstance("qwerty").users.take(5).forEach(::println)
    UserRepository.getInstance("qwerty").users.take(5).forEach(::println)
}