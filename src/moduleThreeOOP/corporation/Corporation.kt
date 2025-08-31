package moduleThreeOOP.corporation

fun main() {
    val director = WorkersRepository.findDirector()
    director?.printInfo()
}