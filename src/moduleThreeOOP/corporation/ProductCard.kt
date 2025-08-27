package moduleThreeOOP.corporation

open class ProductCard(
    var name: String,
    var brand: String,
    var price: Int,
    val productType: ProductType
) {
    open fun printInfo() {
        print("Name ${this.name} Brand ${this.brand} Price ${this.price} Product type ${productType.title} ")
    }
}
