package moduleThreeOOP.corporation

import kotlin.random.Random

data class Consultant(
    override val id: Int,
    override val name: String,
    override val age: Int = 0,
    override val salary: Int
) : Worker(id = id, name = name, age = age, salary = salary, position = Position.CONSULTANT),
    Cleaner {

    override fun clean() {
        println("My position is ${position.title}")
        super.clean()
    }

    fun serveClient(): Int {
        val count = Random.nextInt(0, 100)
        repeat(count) {
            print("Client was served! ")
        }
        println()
        return count
    }

    override fun copy(salary: Int, age: Int): Consultant {
        return Consultant(this.id, this.name, age, salary)
    }

    override fun work() {
        serveClient()
    }
}
