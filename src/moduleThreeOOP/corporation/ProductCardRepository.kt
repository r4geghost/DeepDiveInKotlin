package moduleThreeOOP.corporation

import java.io.File

class ProductCardRepository {

    private val fileProductCards = File("product_card.txt")

    fun registerNewItem(productCard: ProductCard) {
        saveProductCardToFile(productCard)
    }

    private fun saveProductCardToFile(productCard: ProductCard) {
        fileProductCards.appendText("${productCard.name}%${productCard.brand}%${productCard.price}%")
        when (productCard) {
            is FoodCard -> {
                val caloric = productCard.caloric
                fileProductCards.appendText("$caloric%")
            }

            is ShoeCard -> {
                val size = productCard.size
                fileProductCards.appendText("$size%")
            }

            is ApplianceCard -> {
                val wattage = productCard.wattage
                fileProductCards.appendText("$wattage%")
            }
        }
        fileProductCards.appendText("${productCard.productType}\n")
    }

    fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
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
        val cards: MutableList<ProductCard> = loadAllCards()
        for (card in cards) {
            if (card.name == name) {
                cards.remove(card)
                break
            }
        }
        fileProductCards.writeText("")
        for (card in cards) {
            saveProductCardToFile(card)
        }
    }

}