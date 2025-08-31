package moduleThreeOOP.corporation

data class ShoeCard(
    override val name: String,
    override val brand: String,
    override val price: Int,
    val size: Float
) : ProductCard(name = name, brand = brand, price = price, ProductType.SHOE)