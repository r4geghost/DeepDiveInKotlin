package products

fun main() {
    val products = ProductRepository.products

    val filteredProducts = filter(products) { it.productCategory == ProductCategory.CLOTHING }

    val transformedProducts = transform(filteredProducts) { it.copy(productPrice = it.productPrice * 2) }
    val transformedOutput = transform(transformedProducts) { "${it.id} - ${it.productName} - ${it.productPrice}" }

    transformedOutput.forEach { println(it) }
}

private fun <R> transform(productCards: List<ProductCard>, operation: (ProductCard) -> R): List<R> {
    return productCards.map(operation)
}

private fun filter(productCards: List<ProductCard>, isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val result = mutableListOf<ProductCard>()
    for (productCard in productCards) {
        if (isSuitable(productCard)) {
            result.add(productCard)
        }
    }
    return result
}