package moduleThreeOOP.corporation

import java.io.File

// паттерн singleton (работает только с пустым конструктором)
object ProductCardRepository {

    private val fileProductCards = File("product_card.txt")

    // backing field (теневое поле)
    private val _cards = loadAllCards()

    val cards
        // возвращаем неизменяемую копию коллекции
        get() = _cards.toList()

    fun registerNewItem(productCard: ProductCard) = _cards.add(productCard)

    fun saveChanges() {
        val content = StringBuilder()
        for (card in cards) {
            content.append("${card.name}%${card.brand}%${card.price}%${card.productType}\n")
        }
        fileProductCards.writeText(content.toString())
    }

    private fun loadAllCards(): MutableSet<ProductCard> {
        val cards = mutableSetOf<ProductCard>()
        if (!fileProductCards.exists()) fileProductCards.createNewFile()
        val content = fileProductCards.readText().trim()
        if (content.isEmpty()) return cards
        val cardsAsString = content.split("\n")
        for (cardAsString in cardsAsString) {
            val properties = cardAsString.split("%")
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)
            val productCard = when (productType) {
                ProductType.FOOD -> {
                    val caloric = properties[2].toInt()
                    FoodCard(name, brand, price, caloric)
                }

                ProductType.APPLIANCE -> {
                    val wattage = properties[2].toInt()
                    ApplianceCard(name, brand, price, wattage)
                }

                ProductType.SHOE -> {
                    val size = properties[2].toFloat()
                    ShoeCard(name, brand, price, size)
                }
            }
            cards.add(productCard)
        }
        return cards
    }

    fun removeProductCard(name: String) {
        for (card in cards) {
            if (card.name == name) {
                _cards.remove(card)
                break
            }
        }
    }

}