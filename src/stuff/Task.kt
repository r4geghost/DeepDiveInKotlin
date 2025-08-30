package stuff

fun main() {
    task(listOf("user0", "user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9"))
}

fun task(listUser: List<String>): List<String> {
    val users = mutableListOf<String>()
    listUser.forEach { user -> users.add(user) }

    println("Choose action: show, add, remove or remove at: ")
    when (readln()) {
        "SHOW" -> printUsers(users)

        "ADD" -> {
            println("Input string to add: ")
            users.add(readln())
            printUsers(users)
        }

        "REMOVE" -> {
            println("Input string to remove: ")
            users.remove(readln())
            printUsers(users)
        }

        "REMOVE_AT" -> {
            println("Input string index to remove: ")
            users.removeAt(readln().toInt())
            printUsers(users)
        }
        else -> println("Некорректное значение")
    }

    return users.toList()
}

fun printUsers(users: List<String>) {
    users.forEach { println(it) }
}

class Car(
    val make: String,
    val model: String,
    val year: Int,
    val vin: String,
    val color: String,
)

fun serialize(car: Car): String {
    return "${car.make}%${car.model}%${car.year}%${car.vin}%${car.color}"
}

fun deserialize(carAsString: String): Car {
    val input = carAsString.split("%")
    return Car(input[0], input[1], input[2].toInt(), input[3], input[4])
}


data class DataOrder(
    val id: Int,
    val status: String,
    val type: String
)

fun removeCompletedDataOrders(orders: List<DataOrder>, typeToRemove: String): List<DataOrder> {
    val filteredDataOrders = mutableListOf<DataOrder>()
    for (order in orders) {
        if (order.status == "completed" && order.type != typeToRemove) {
            filteredDataOrders.add(order)
        }
    }
    return filteredDataOrders.toList()
}