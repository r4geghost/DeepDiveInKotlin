package test

fun main() {
    val dictionary = mutableMapOf(
        "hello" to "bonjour",
        Pair("thank you", "merci")
    )
    dictionary.put("goodbye", "au revoir")
    // аналогично
    dictionary["excuse me"] = "excusez-moi"

    println(dictionary.get("hello"))
    // аналогично
    println(dictionary["goodbye"])

    for (entry in dictionary) {
        println("${entry.key}: ${entry.value}")
    }

    var nullableMap: Map<String, String>? = mapOf(
        "hello" to "bonjour",
        "thank you" to "merci"
    )
    // здесь нельзя использовать обращение по индексу
    println(nullableMap?.get("hello"))
}