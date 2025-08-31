package moduleThreeOOP.corporation

abstract class Worker(
    open val id: Int,
    open val name: String,
    open val age: Int = 0,
    open val salary: Int,
    val position: Position
) {
    abstract fun copy(
        salary: Int = this.salary,
        age: Int = this.age
    ) : Worker

    open fun work() {
        println("I'm working now...")
    }

    fun printInfo() {
        println(this)
    }
}
