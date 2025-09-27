package dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

object Display {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository
    private lateinit var queries: Flow<String>

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

    // Реактивный стиль
    init {
        // подписываемся на обновление flow
        queries.onEach {// вызывается на каждый новый элемент
            searchButton.isEnabled = false
            resultArea.text = "Loading..."
        }.map {
            repository.loadDefinition(it)
        }.map {
            it.joinToString("\n\n").ifEmpty { "Not found" }
        }.onEach {
            resultArea.text = it
            searchButton.isEnabled = true
        }.launchIn(scope) // вызывает collect() внутри указанного scope
    }

    private fun loadDefinition() {
    }
}