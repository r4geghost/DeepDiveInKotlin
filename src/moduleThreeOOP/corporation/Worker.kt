package moduleThreeOOP.corporation

open class Worker(
    val id: Int,
    val name: String,
    val age: Int = 0,
    private var salary: Int,
    val position: Position
) {

    fun setSalary(value: Int) {
        if (value < salary) {
            println("The new salary is too small...")
        } else {
            salary = value
        }
    }

    fun getSalary() = salary

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
