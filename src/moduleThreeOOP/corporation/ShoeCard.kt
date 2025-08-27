package moduleThreeOOP.corporation

class ShoeCard(
    name: String,
    brand: String,
    price: Int,
    val size: Float
): ProductCard(name = name, brand = brand, price = price, ProductType.SHOE) {

    override fun printInfo() {
        super.printInfo()
        println("Size $size")
    }
}
