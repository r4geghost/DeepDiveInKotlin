package dictionary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("value") val value: String,
    @SerialName("description") val description: String
)
