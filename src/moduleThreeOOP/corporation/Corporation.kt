package moduleThreeOOP.corporation

fun main() {
    val director = WorkersRepository.findDirector() ?: throwDirectorIsRequired()
    director.printInfo()
}

// Nothing has no instances. You can use Nothing to represent "a value that never exists":
// for example, if a function has the return type of Nothing,
// it means that it never returns (always throws an exception).

// Any -> общий родитель, Nothing -> наследник ВСЕХ классов
fun throwDirectorIsRequired(): Nothing {
    throw IllegalStateException("Director is required for this program. Please add him to the file workers.txt")
}