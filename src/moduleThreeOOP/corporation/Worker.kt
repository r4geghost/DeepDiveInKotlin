package moduleThreeOOP.corporation

abstract class Worker(
    val id: Int,
    val name: String,
    val age: Int = 0,
    val salary: Int,
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

    override fun toString(): String {
        return "${position.title}(id=$id, name='$name', age=$age, salary=$salary, position=$position)"
    }
}
