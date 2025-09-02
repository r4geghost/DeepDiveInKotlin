package test

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val file = File("item.json")
    writeToFile(file)
    readFromFile(file).forEach { println(it) }
}

private fun writeToFile(file: File) {
    val items = mutableListOf<Item>()
    while (true) {
        print("Enter id or 0 to exit: ")
        val id = readln().toInt()
        if (id == 0) break
        print("Enter name: ")
        val name = readln()
        val item = Item(id, name)
        items.add(item)
    }
    val itemsAsString = Json.encodeToString(items)
    file.writeText(itemsAsString)
}

private fun readFromFile(file: File): List<Item> {
    return Json.decodeFromString<List<Item>>(file.readText().trim())
}