package moduleThreeOOP.corporation


class Assistant(
    id: Int,
    name: String,
    age: Int = 0,
    salary: Int
) : Worker(id = id, name = name, age = age, salary = salary, position = Position.ASSISTANT),
    Cleaner,
    Supplier {

    override fun copy(salary: Int, age: Int): Assistant {
        return Assistant(this.id, this.name, age, salary)
    }

    override fun work() {
        println("I'm answering the phone right now...")
    }

    override fun clean() {
        println("My position is ${position.title}")
        super.clean()
    }
}
