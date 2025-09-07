package dictionary

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val file = File("FunctionalProgramming/src/main/kotlin/dictionary/dictionary.json")
    val content = file.readText().trim()
    val dictionary: List<Entry> = Json.decodeFromString(content)
    showDescription(dictionary)
}

private fun showDescription(dictionary: List<Entry>) {
    while (true) {
        print("Enter word or 0 to exit: ")
        val word = readln().lowercase()
        if (word == "0") break
        dictionary.find { it.value == word }?.let { println(it.description) } ?: println("Not found")
    }
}