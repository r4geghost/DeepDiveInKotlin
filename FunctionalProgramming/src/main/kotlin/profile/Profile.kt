package profile

fun main() {
    val profiles = ProfilesRepository.profiles

    // создаем функцию и записываем в переменную
    // если лямбда-выражение принимает один параметр, то компилятор даст ему имя "it" и его можно использовать
    val olderThan25: (Person) -> Boolean = { it.age > 25 }
    var filteredProfiles = filter(profiles, olderThan25)

    // но можно сразу передать функцию в метод
    // если функция - последний параметр, то ее можно записать после закрытых круглых скобок
    filteredProfiles = filter(filteredProfiles) { it.gender == Gender.MALE }

    filteredProfiles = filter(filteredProfiles) { it.firstName.startsWith("A") }

    filteredProfiles = filter(filteredProfiles) { it.age < 30 }

    val transformed = transform(filteredProfiles) { it.copy(age = it.age + 1) }

    transformed.forEach { println(it) }
}

// generic функция
private fun <R> transform(profiles: List<Person>, operation: (Person) -> R): List<R> {
    val result = mutableListOf<R>()
    for (person in profiles) {
        result.add(operation(person))
    }
    return result
}

// синтаксис функций = "(тип на вход) -> тип на выход"
// функция высшего порядка = принимает другие функции в качестве параметра или возвращать их
private fun filter(profiles: List<Person>, isSuitable: (Person) -> Boolean): List<Person> {
    val result = mutableListOf<Person>()
    for (profile in profiles) {
        if (isSuitable(profile)) {
            result.add(profile)
        }
    }
    return result
}