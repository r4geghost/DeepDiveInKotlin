package dictionary

import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

object Display {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository
    private var loadingJob: Job? = null

    private val enterWordLabel = JLabel("Enter word: ")
    private val searchField = JTextField(20).apply {
        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent?) {
                loadDefinition()
            }
        })
    }
    private val searchButton = JButton("Search").apply {
        addActionListener {
            loadDefinition()
        }
    }

    private val resultArea = JTextArea(25, 50).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
    }

    private val topPanel = JPanel().apply {
        add(enterWordLabel)
        add(searchField)
        add(searchButton)
    }

    private val mainFrame = JFrame("Dictionary App").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(resultArea), BorderLayout.CENTER)
        pack()
    }

    fun show() {
        mainFrame.isVisible = true
    }

    // Императивный стиль
    private fun loadDefinition() {
        // отменяем запрос, если пользователь ввел новое значение
        loadingJob?.cancel()
        loadingJob = scope.launch {
            searchButton.isEnabled = false
            delay(500) // ждем пока пользователь введет значение
            resultArea.text = "Loading..."
            val word = searchField.text.trim()
            val definition = repository.loadDefinition(word)
            resultArea.text = definition.joinToString("\n\n").ifEmpty { "Not found" }
            searchButton.isEnabled = true
        }

    }
}