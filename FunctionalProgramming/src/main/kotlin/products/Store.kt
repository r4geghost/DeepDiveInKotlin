package products

fun main() {
    val products = ProductRepository.products
    products.forEach { println(it) }
}