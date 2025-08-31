package homework

data class UserData(
    val name: String?,
    val email: String?,
    val age: Int?
)

interface Repository {
    /**
     * Возвращает список пользователей, некоторые элементы или их поля могут быть null.
     */
    fun getUsers(): List<UserData?>
}

class UserViewModel(private val repository: Repository) {

    /**
     * Реализуйте метод getUserDescriptions, который:
     * 1. Получает данные из репозитория.
     * 2. Удаляет null элементы списка.
     * 3. Заменяет null значения в полях name, email и age на дефолтные.
     * 4. Формирует строки в формате: "Name: [name], Email: [email], Age: [age]".
     *
     * @return Список строк с описаниями пользователей.
     */
    fun getUserDescriptions(): List<String> {
        return repository.getUsers()
            .toMutableList()
            .filterNotNull()
            .map { "Name: ${it.name ?: "Unknown Name"}, Email: ${it.email ?: "Unknown Email"}, Age: ${it.age ?: 0}" }
    }
}