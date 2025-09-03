package profile

fun main() {
    val profiles = ProfilesRepository.profiles
    profiles.forEach { println(it) }
}