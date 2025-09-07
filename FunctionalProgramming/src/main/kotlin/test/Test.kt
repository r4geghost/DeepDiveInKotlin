package test

import extentions.myApply
import extentions.myWith

fun main() {
    exampleApply()
    exampleWith()
}

private fun exampleWith() {
    myWith(mutableListOf<Int>()) {
        while (true) {
            print("Enter your input: ")
            val nextNumber = readln().toInt().takeIf { it != 0 } ?: break
            add(nextNumber) // напрямую вызываем метод add() у коллекции
        }
        println("Max: ${max()}")
        println("Min: ${min()}")
        this
    }.forEach {
        println(it)
    }
}

private fun exampleApply() {
    mutableListOf<Int>().myApply {// лямбды с ресивером (this: MutableList<Int>)
        while (true) {
            print("Enter your input: ")
            val nextNumber = readln().toInt().takeIf { it != 0 } ?: break
            add(nextNumber) // напрямую вызываем метод add() у коллекции
        }
    }.forEach {// лямбды с аргументом (it или свой)
        println(it)
    }
}