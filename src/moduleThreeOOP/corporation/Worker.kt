package moduleThreeOOP.corporation

open class Worker(
    val id: Int,
    val name: String,
    val age: Int = 0,
    val position: Position
) {

    var salary: Int = 15000
        set(value) {
            if (value < salary) {
                println("The new salary is too small...")
            } else {
                field = value
            }
        }

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
