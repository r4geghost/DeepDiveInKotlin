package moduleThreeOOP.corporation

class Director(
    id: Int,
    name: String,
    age: Int,
    salary: Int
) : Worker(id = id, name = name, age = age, salary = salary, position = Position.DIRECTOR),
    Supplier {
    fun takeCoffee(assistant: Assistant) {
        val drinkName = assistant.bringCoffee(count = 1, drinkName = "Cappucino")
        println("Thank you ${assistant.name}! The $drinkName was very delicious")
    }

    fun makeConsultantWork(consultant: Consultant) {
        val customersServerd = consultant.serveClient()
        println("Consultant ${consultant.name} served $customersServerd clients! ")
    }

    override fun work() {
        println("I'm drinking coffee")
    }
}
