package moduleThreeOOP.corporation

fun main() {
    val director = WorkersRepository.findDirector()
    director?.printInfo()
    val assistant = WorkersRepository.findAssistant()
    assistant?.printInfo()

    val directorSalary = director?.salary ?: 0 // если левая часть == null, то = 0
    val assistantSalary = assistant?.salary ?: 0
    val sum = assistantSalary + directorSalary
    println(sum)

    val a: Unit = method() // Unit class -
    // The type with only one value: the Unit object. This type corresponds to the void type in Java.
    println(a.hashCode())
}

fun method() {}