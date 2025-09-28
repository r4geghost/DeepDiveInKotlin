package dictionary

sealed interface ScreenState {
    data object Initial : ScreenState
    data object Loading : ScreenState
    data object NotFound : ScreenState
    data class DefinitionsLoaded(val definitions: List<String>) : ScreenState
}