package dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import javax.swing.*

object Display {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository

    private val enterWordLabel = JLabel("Enter word: ")
    private val searchField = JTextField(20)
    private val searchButton = JButton("Search").apply {
        addActionListener {
            scope.launch {
                showResult()
            }
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

    private suspend fun showResult() {
        searchButton.isEnabled = false
        resultArea.text = "Loading..."
        val word = searchField.text.trim()
        val definition = repository.loadDefinition(word)
        resultArea.text = definition.joinToString("\n\n").ifEmpty { "Not found" }
        searchButton.isEnabled = true
    }
}