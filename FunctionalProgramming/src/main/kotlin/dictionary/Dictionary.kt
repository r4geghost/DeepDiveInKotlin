package dictionary

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.time.measureTime

fun main() {
    val file = File("FunctionalProgramming/src/main/kotlin/dictionary/dictionary.json")
    val content = file.readText().trim()
    val dictionary: List<Entry> = Json.decodeFromString(content)
    // преобразуем в Map с помощью associate
    val dictionaryMap = dictionary.associate { it.value to it.description }
    // можно также:
    // val dictionaryMap = dictionary.map { it.value to it.description }.toMap()
    // преобразуем та
    val mapAsString = Json.encodeToString(dictionaryMap)
    file.writeText(mapAsString)
    showDescription(dictionaryMap)
}

private fun showDescription(dictionary: Map<String, String>) {
    while (true) {
        print("Enter word or 0 to exit: ")
        val word = readln().lowercase()
        if (word == "0") break
        val time = measureTime {
            dictionary[word]?.let { println(it) } ?: println("Not found")
        }
        println("Duration: $time")
    }
}