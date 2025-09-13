package builder

fun main() {
    val coffee = Drink.Builder()
        .additives(listOf("Vanilla syrup"))
        .build()
    println(coffee)

    val tea = Drink.Builder()
        .type("Tea")
        .temperature("Medium")
        .diningOption("Take off")
    println(tea)
}