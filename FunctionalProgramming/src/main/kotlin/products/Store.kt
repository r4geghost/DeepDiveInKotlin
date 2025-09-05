package products

fun main() {
    val products = ProductRepository.products
        .filter { it.productCategory == ProductCategory.CLOTHING }
        .transform { it.copy(productPrice = it.productPrice * 2) }
        .transform { "${it.id} - ${it.productName} - ${it.productPrice}" }

    products.forEach { println(it) }
//
//    val filteredProducts = filter(products) { it.productCategory == ProductCategory.CLOTHING }
//
//    val transformedProducts = transform(filteredProducts) { it.copy(productPrice = it.productPrice * 2) }
//    val transformedOutput = transform(transformedProducts) { "${it.id} - ${it.productName} - ${it.productPrice}" }
//
//    transformedOutput.forEach { println(it) }
}

private fun <R> List<ProductCard>.transform(operation: (ProductCard) -> R): List<R> = this.map(operation)

private fun List<ProductCard>.filter(isSuitable: (ProductCard) -> Boolean): List<ProductCard> {
    val result = mutableListOf<ProductCard>()
    for (productCard in this) {
        if (isSuitable(productCard)) {
            result.add(productCard)
        }
    }
    return result
}