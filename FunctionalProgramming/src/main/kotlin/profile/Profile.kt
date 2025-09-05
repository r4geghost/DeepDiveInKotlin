package profile

import extentions.filter
import extentions.transform

fun main() {
    val profiles = ProfilesRepository.profiles
        .filter { it.age > 25 }
        .filter { it.gender == Gender.MALE }
        .filter { it.firstName.startsWith("A") }
        .filter { it.age < 30 }
        .transform { it.copy(age = it.age + 1) }

    profiles.forEach { println(it) }

    // создаем функцию и записываем в переменную
    // если лямбда-выражение принимает один параметр, то компилятор даст ему имя "it" и его можно использовать
//    val olderThan25: (Person) -> Boolean = { it.age > 25 }
//    var filteredProfiles = filter(profiles, olderThan25)
//
    // но можно сразу передать функцию в метод
    // если функция - последний параметр, то ее можно записать после закрытых круглых скобок
//    filteredProfiles = filter(filteredProfiles) { it.gender == Gender.MALE }
//
//    filteredProfiles = filter(filteredProfiles) { it.firstName.startsWith("A") }
//
//    filteredProfiles = filter(filteredProfiles) { it.age < 30 }
//
//    val transformed = transform(filteredProfiles) { it.copy(age = it.age + 1) }
//
//    transformed.forEach { println(it) }
}