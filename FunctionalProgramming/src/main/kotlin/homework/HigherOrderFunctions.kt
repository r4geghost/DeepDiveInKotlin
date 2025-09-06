package homework

import extentions.filter

/*
    В приложении, над которым вы работаете, нужно реализовать функциональность для отображения списка продуктов.
    Эти данные поступают из репозитория и должны быть обработаны перед отправкой в пользовательский интерфейс.

    Каждый продукт представлен объектом класса Product, содержащим:
        1) id — уникальный идентификатор продукта (целое число).
        2) name — название продукта (строка).
        3) price — цена продукта (вещественное число).
        4) category — категория продукта (строка).

    Вам нужно написать метод, который выполнит следующие действия:
        1) Исключит из списка продукты, у которых цена меньше 100.
        2) Оставит только продукты из категорий "Electronics" или "Books".
        3) Отсортирует оставшиеся продукты по цене в порядке возрастания.
        4) Преобразует каждый продукт в строку формата:
            "Product ID: <id> | Name: <name> | Price: $<price>".
        5) Вернёт итоговый список строк, который будет отображён в пользовательском интерфейсе.
*/

/**
 * Класс для представления информации о продукте.
 *
 * @property id Уникальный идентификатор продукта.
 * @property name Название продукта.
 * @property price Цена продукта.
 * @property category Категория продукта.
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String
)

/**
 * Метод, который обрабатывает список продуктов для отображения в UI.
 *
 * @param products Список продуктов, полученных из репозитория.
 * @return Список строк, готовых для отображения в UI.
 */
fun processProductsForUI(products: List<Product>): List<String> {
    return products
        .filter { it.price >= 100 && it.category in listOf("Electronics", "Books") }
        .sortedBy { it.price }
        .map { "Product ID: ${it.id} | Name: ${it.name} | Price: \$${it.price}" }
}

fun main() {
    val products = listOf(
        Product(1, "Laptop", 999.99, "Electronics"),
        Product(2, "Notebook", 12.99, "Books"),
        Product(3, "Tablet", 299.99, "Electronics"),
        Product(4, "Novel", 15.49, "Books"),
        Product(5, "Pen", 2.99, "Stationery")
    )
    println(processProductsForUI(products))
}