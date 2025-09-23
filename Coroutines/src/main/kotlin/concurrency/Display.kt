package concurrency

import entities.Book
import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Font
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.concurrent.Executors
import javax.swing.*

object Display {

    private val dispatcher = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).asCoroutineDispatcher()
    private val scope = CoroutineScope(CoroutineName("My coroutine") + dispatcher)

    private val infoArea = JTextArea().apply {
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        isEditable = false
        Executors.newSingleThreadExecutor()
    }

    private val button = JButton("Load book").apply {
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        addActionListener {
            isEnabled = false
            infoArea.text = "Loading book information...\n"

            // jobs - коллекция корутин
            val jobs = mutableListOf<Job>()
            repeat(10) {
                // функция launch создает корутину для каждой загрузки книги
                scope.launch {
                    val book = loadBook()
                    infoArea.append("Book $it: ${book.title}\nYear: ${book.year}\nGenre: ${book.genre}\n\n")
                }.also { jobs.add(it) }
            }
            // ждем завершения всех корутин из коллекции
            scope.launch {
                jobs.joinAll()
                isEnabled = true
            }
        }
    }

    private val timerLabel = JLabel("Time: 00:00").apply { font = Font(Font.SANS_SERIF, Font.PLAIN, 16) }

    private val topPanel = JPanel(BorderLayout()).apply {
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        add(timerLabel, BorderLayout.WEST)
        add(button, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 400)
        // при закрытии окна отменяем работу скоупа
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                scope.cancel()
            }
        })
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun startTimer() {
        scope.launch {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = String.format("Time: %02d:%02d", minutes, seconds)
                delay(1000)
                totalSeconds++
            }
        }
    }

    // suspend - функция, которая может прерваться/приостановиться
    private suspend fun loadBook(): Book {
        // тело функции вынесено в фоновый поток
        return withContext(Dispatchers.Default) {
            longOperation()
            Book("1984", 1949, "Dystopia")
        }
    }

    private fun longOperation() {
        mutableListOf<Int>().apply {
            repeat(300_000) {
                add(0, it)
            }
        }
    }
}