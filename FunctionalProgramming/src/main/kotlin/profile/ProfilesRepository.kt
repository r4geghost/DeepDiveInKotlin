package profile

import kotlinx.serialization.json.Json
import java.io.File

object ProfilesRepository {

    private val file = File("FunctionalProgramming/profiles.json")

    private val _profiles = loadProfiles()
    val profiles
        get() = _profiles.toList()

    private fun loadProfiles(): List<Person> {
        val content = file.readText().trim()
        return Json.decodeFromString(content)
    }
}