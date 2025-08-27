package moduleThreeOOP.corporation

class ApplianceCard(
    name: String,
    brand: String,
    price: Int,
    val wattage: Int
): ProductCard(name = name, brand = brand, price = price, ProductType.APPLIANCE) {

    override fun printInfo() {
        super.printInfo()
        println("Wattage $wattage")
    }
}
