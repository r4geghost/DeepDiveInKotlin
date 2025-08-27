package stuff

import java.io.File

fun main() {
    chooseOpt()
}

fun chooseOpt() {
    val listFile = File("List.txt")

    while (true) {
        println("\nChoose operation code:")
        println("1 - Exit")
        println("2 - Add to list")
        println("3 - Show list")

        when (readln().toIntOrNull()) {
            1 -> return
            2 -> createListWhatToDo(listFile)
            3 -> showList(listFile)
            else -> println("Invalid code! Try again.")
        }
    }
}

fun createListWhatToDo(listFile: File) {
    print("What to do: ")
    val task = readln()
    listFile.appendText("$task\n")
    println("Task added!")
}

fun showList(listFile: File) {
    if (!listFile.exists()) {
        println("List is empty! Add tasks first.")
        return
    }
    println("\n--- Your To-Do List ---")
    listFile.forEachLine { println(it) }
    println("----------------------")
}

