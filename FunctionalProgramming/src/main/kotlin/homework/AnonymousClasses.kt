package homework

interface ClickListener {
    fun onClick(elementId: String): String
}

class ClickProcessor {
    private val clickListeners = mutableMapOf<String, ClickListener>()

    fun registerClickListener(elementId: String, listener: ClickListener) {
        clickListeners[elementId] = listener
    }

    fun processClick(elementId: String): String {
        val listener = clickListeners[elementId]
        return listener?.onClick(elementId) ?: "No listener registered for $elementId"
    }
}

// Реализуйте метод setupClickListeners
fun setupClickListeners(clickProcessor: ClickProcessor) {
    // TODO: Зарегистрируйте слушателей для "button1", "button2" и "button3" с помощью анонимных классов.
}