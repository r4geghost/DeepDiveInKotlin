package dictionary

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

@FlowPreview
object Display {

    /*
    private val state = MutableSharedFlow<ScreenState>(
        replay = 1, // показывает, сколько старых эмитов нужно отдавать новым подписчикам
        extraBufferCapacity = 0, // можно дополнительно расширить буфер (по умолчанию = replay)
        BufferOverflow.DROP_OLDEST // стратегия при переполнении буфера
    )
     */

    // аналогичные настройки сразу указаны в MutableStateFlow (+ нужно передать начальное состояние)
    private val state = MutableStateFlow<ScreenState>(ScreenState.Initial)

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository

    private val queries = MutableSharedFlow<String>()

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
        queries.debounce(500) // добавляем особую задержку (если за это время пришел новый элемент, старый отменяется)
            .onEach {// вызывается на каждый новый элемент
                state.emit(ScreenState.Loading) // эмитим состояние Loading когда пришел новый элемент в поток
            }.map {
                if (it.isEmpty()) {
                    state.emit(ScreenState.Initial)
                } else {
                    val result = repository.loadDefinition(it)
                    if (result.isEmpty()) {
                        state.emit(ScreenState.NotFound)
                    } else {
                        state.emit(ScreenState.DefinitionsLoaded(result))
                    }
                }
            }
            // retry под капотом поймает исключение и обработает
            .retry {// при ошибке переподписываемся на флоу, эмича состояние ошибки
                println("Exception: $it")
                state.emit(ScreenState.Exception)
                true
            }
            .launchIn(scope) // вызывает collect() внутри указанного scope

        // текущее состояние экрана всегда будет лежать внутри одного объекта mutable shared flow
        state.onEach {
            when (it) {
                is ScreenState.DefinitionsLoaded -> {
                    val definitions = it.definitions.joinToString("\n\n")
                    resultArea.text = definitions
                    searchButton.isEnabled = true
                }

                ScreenState.Initial -> {
                    resultArea.text = ""
                    searchButton.isEnabled = false
                }

                ScreenState.Loading -> {
                    resultArea.text = "Loading..."
                    searchButton.isEnabled = false
                }

                ScreenState.NotFound -> {
                    resultArea.text = "Not found"
                    searchButton.isEnabled = true
                }

                ScreenState.Exception -> {
                    resultArea.text = "Something went wrong..."
                    searchButton.isEnabled = true
                }
            }
        }.launchIn(scope)
    }

    private fun loadDefinition() {
        scope.launch {
            // при клике на кнопку будем передавать текст в канал
            queries.emit(searchField.text.trim())
        }
    }
}