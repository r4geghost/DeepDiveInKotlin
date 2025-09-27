package dictionary

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

@FlowPreview
object Display {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository

    // создаем объект канал, куда корутины могут класть элементы,
    // и из которого можно забирать элементы через consumeEach {}
    private val queries = Channel<String>()

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
        // (через метод consumeAsFlow() канал преобразуется в объект flow)
        queries.consumeAsFlow()
            .debounce(500) // добавляем задержку (если за это время пришел новый элемент, старый отменяется)
            .onEach {// вызывается на каждый новый элемент
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
        scope.launch {
            // при клике на кнопку будем передавать текст в канал
            queries.send(searchField.text.trim())
        }
    }
}