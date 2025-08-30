package moduleThreeOOP.corporation

class Director(
    id: Int,
    name: String,
    age: Int,
    salary: Int
) : Worker(id = id, name = name, age = age, salary = salary, position = Position.DIRECTOR),
    Supplier {

    override fun copy(salary: Int, age: Int): Director {
        return Director(this.id, this.name, age, salary)
    }

    override fun work() {
        println("I'm drinking coffee")
    }
}
