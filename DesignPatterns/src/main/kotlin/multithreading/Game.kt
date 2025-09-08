package multithreading

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    print("Enter number from 0 to 1_000_000_000:")
    val input = readln().toInt()
    var win = false
    thread {
        var seconds = 0
        while (!win) {
            Thread.sleep(1000)
            println(seconds++)
        }
    }
    thread {
        while (true) {
            val guess = Random.nextInt(1_000_000_001)
            if (guess == input) {
                println("I win. Your number is: $guess")
                win = true
                break
            }

        }
    }
}