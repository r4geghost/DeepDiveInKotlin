package moduleThreeOOP.corporation

data class FoodCard(
    override val name: String,
    override val brand: String,
    override val price: Int,
    val caloric: Int
) : ProductCard(name = name, brand = brand, price = price, ProductType.FOOD)
