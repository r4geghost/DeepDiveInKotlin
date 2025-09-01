package moduleThreeOOP.exception

fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    try {
        println(a / b)
    } catch (e: ArithmeticException) {
        println("You can't divide by zero")
    } catch (e: NumberFormatException) {
        println("Please input a number")
    } catch (e: Exception) {
        println("Common error : ${e.message}")
    }
    println("After try/catch")
}