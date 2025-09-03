package products

import kotlinx.serialization.json.Json
import java.io.File

object ProductRepository {

    private val file = File("FunctionalProgramming/src/main/kotlin/products/products.json")

    private val _products = loadProducts()
    val products
        get() = _products.toList()

    private fun loadProducts(): List<ProductCard> {
        val content = file.readText().trim()
        return Json.decodeFromString(content)
    }
}