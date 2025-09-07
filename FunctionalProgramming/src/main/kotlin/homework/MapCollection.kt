package homework

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    Напишите extension-функцию для коллекции Map, которая будет преобразовывать все значения в коллекции по заданной функции.

    Требования:
    1) Extension-функция должна называться transformValues.
    2) На вход она принимает преобразующую функцию (transform), которая преобразует значения из исходного типа V в новый тип R.
    3) Возвращаемое значение — новый Map, где ключи остаются без изменений, а значения преобразованы с использованием переданной функции.

*/

/**
 * Расширение для преобразования значений в `Map`.
 * @param transform Функция для преобразования значений.
 * @return Новый `Map` с теми же ключами, но преобразованными значениями.
 */
fun <K, V, R> Map<K, V>.transformValues(transform: (V) -> R): Map<K, R> {
    val result = mutableMapOf<K, R>()
    for ((key, value) in this) {
        result[key] = transform(value)
    }
    return result
}

@Serializable
data class UserForMap(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("age") val age: Int,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("address") val address: String,
    @SerialName("phone") val phone: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("role") val role: String
)

/**
 * Преобразует список пользователей в Map, где ключ — `id`, а значение — объект `User`.
 * @param users Список пользователей.
 * @return Коллекция Map пользователей.
 */
fun usersToMap(users: List<UserForMap>): Map<Int, UserForMap> = users.associateBy { it.id }