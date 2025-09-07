package products

import extentions.myAlso

fun main() {
    ProductRepository.products
        /*
            Функция also в Kotlin — это стандартная функция-расширение (и scope function),
            которая позволяет выполнить дополнительные действия с объектом в цепочке вызовов, не изменяя сам объект.
            Она похожа на let, но имеет два ключевых отличия:
                1) Возвращает исходный объект (а не результат лямбды, как let).
                2) Доступ к объекту через it (или можно задать имя вручную).
        */
        .also { println("Logging: filter by ProductCategory.CLOTHING") }
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .also { println("Logging: convert to string") }
        .map { "${it.id} - ${it.productName} - ${it.productPrice * 2}" }
        .myAlso { println("Logging: print info") }
        .forEach(::println) // method reference - ссылка на метод
                            // (функция не создается - в цикле вызвать какую-то функцию и передай ей параметр)
}