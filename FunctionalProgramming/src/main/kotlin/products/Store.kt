package products

fun main() {
    val products = ProductRepository.products

    var filteredProducts = filter(products) { it.productPrice > 500 }
    filteredProducts = filter(filteredProducts) { it.productRating > 4 }
    filteredProducts = filter(filteredProducts) { it.productCategory == ProductCategory.SPORTS }

    filteredProducts.forEach { println(it) }
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