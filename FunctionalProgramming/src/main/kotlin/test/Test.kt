package test

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val books = File("books.json")
    writeToFile(books)
    readFromFile(books).forEach { println(it) }
}

private fun writeToFile(file: File) {
    val books = mutableListOf<Book>()
    while (true) {
        print("Enter book's year or 0 to exit: ")
        val year = readln().toInt()
        if (year == 0) break
        print("Enter book's title: ")
        val title = readln()
        print("Enter book's author: ")
        val author = readln()
        val book = Book(title, author, year)
        books.add(book)
    }
    val booksAsString = Json.encodeToString(books)
    file.writeText(booksAsString)
}

private fun readFromFile(file: File): List<Book> {
    return Json.decodeFromString<List<Book>>(file.readText().trim())
}