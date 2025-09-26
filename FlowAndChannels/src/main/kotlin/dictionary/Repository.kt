package dictionary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

object Repository {

    private const val BASE_ULR = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val API_KEY = "PNTI6z/FDA9Z5c6ySdGndg==pLFQ9sI3ftVGyTaa"
    private const val HEADER_KEY = "X-Api-Key"

    // игнорируем неизвестные поля в json
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadDefinition(word: String): List<String> {
        return withContext(Dispatchers.IO) {
            var connection: HttpURLConnection? = null
            try {
                val url = URL(BASE_ULR + word)
                connection = (url.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(HEADER_KEY, API_KEY)
                }
                connection.inputStream.bufferedReader().readText().let {
                    // строка json -> объект класса Definition
                    json.decodeFromString<Definition>(it).mapDefinitionToList()
                }
            } catch (e: Exception) {
                println("Failed to load definition: ${e.message}")
                listOf() // пока так, потом изменим
            } finally {
                // закрываем соединение
                connection?.disconnect()
            }
        }
    }

    private fun Definition.mapDefinitionToList(): List<String> {
        return definition.split(Regex("\\d\\. ")).map { it.trim() }.filter { it.isNotEmpty() }
    }
}