package products

import extentions.myForEach

fun main() {
    ProductRepository.products
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .map { "${it.id} - ${it.productName} - ${it.productPrice * 2}" }
        .myForEach { println(it) }
}