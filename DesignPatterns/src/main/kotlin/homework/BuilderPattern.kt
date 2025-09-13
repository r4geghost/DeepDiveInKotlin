package homework

/*
    Вам необходимо создать объект класса Product, используя Product.Builder.
    Конструктор Product скрыт, и создать объект можно только через билдер.

    Создаваемый объект должен соответствовать следующим параметрам:
        Название: "Smartphone"
        Цена: 999.99
        Производитель: "TechCorp"
        Гарантия: 24

    Для этого используйте следующие методы класса Product.Builder:
        name(name: String): Builder
        price(price: Double): Builder
        manufacturer(manufacturer: String): Builder
        warranty(months: Int): Builder
        build(): Product
    Создайте объект Product, используя данные методы, установите все параметры и передайте его в переменную product.
 */

data class Product(
    val name: String,
    val price: Double,
    val manufacturer: String,
    val warranty: Int
) {
    class Builder {
        private var name: String = "Smartphone"
        private var price: Double = 999.99
        private var manufacturer: String = "TechCorp"
        private var warranty: Int = 24

        fun name(name: String): Builder = this.apply { this.name = name }
        fun price(price: Double): Builder = this.apply { this.price = price }
        fun manufacturer(manufacturer: String): Builder = this.apply { this.manufacturer = manufacturer }
        fun warranty(months: Int): Builder = this.apply { this.warranty = months }

        fun build(): Product {
            return Product(name, price, manufacturer, warranty)
        }
    }
}

fun task(): Product {
    val product = Product.Builder()
        .name("Keyboard")
        .price(8999.99)
        .manufacturer("Dark Project")
        .warranty(12)
        .build()

    return product
}

fun main() {
    val product = task()
    println(product)
}