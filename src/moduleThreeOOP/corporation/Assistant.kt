package moduleThreeOOP.corporation


class Assistant(
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
) : Worker(id = id, name = name, age = age, salary = salary, position = Position.ASSISTANT),
    Cleaner,
    Supplier {
    fun bringCoffee(count: Int, drinkName: String): String {
        repeat(count) {
            println("Встать со стула")
            println("Подойти к кофемашине")
            println("Проверить наличие чашки")
            println("Проверить наличие кофе")
            println("Нажать на кнопку приготовления кофе $drinkName")
            println("Дождаться пока кофе $drinkName будет готов")
            println("Забрать чашку кофе")
            println("Доставить чашку кофе")
        }
        return "Espresso"
    }

    override fun work() {
        println("I'm answering the phone right now...")
    }

    override fun clean() {
        println("My position is ${position.title}")
        super.clean()
    }
}
