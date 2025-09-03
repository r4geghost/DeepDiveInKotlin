package homework

fun main() {
    val clickProcessor = ClickProcessor()
    setupClickListeners(clickProcessor)
    val result = clickProcessor.processClick("button1")
    println(result)
}

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
    // "button1" — возвращает сообщение "Button 1 clicked!".
    clickProcessor.registerClickListener("button1", object : ClickListener {
        override fun onClick(elementId: String): String {
            return "Button 1 clicked!"
        }
    })
    // "button2" — возвращает сообщение "Button 2 clicked!".
    clickProcessor.registerClickListener("button2", object : ClickListener {
        override fun onClick(elementId: String): String {
            return "Button 2 clicked!"
        }
    })
    // "button3" — возвращает сообщение "Button 3 clicked!".
    clickProcessor.registerClickListener("button3", object : ClickListener {
        override fun onClick(elementId: String): String {
            return "Button 3 clicked!"
        }
    })
}