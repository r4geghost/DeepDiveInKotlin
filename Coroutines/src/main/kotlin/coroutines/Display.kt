package coroutines

import entities.Author
import entities.Book
import kotlinx.coroutines.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Font
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*
import kotlin.concurrent.thread

object Display {

    // создание скоупа (CoroutineScope - функция, не конструктор)
    private val scope = CoroutineScope(CoroutineName("My coroutine"))

    private val infoArea = JTextArea().apply {
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        isEditable = false
    }

    private val button = JButton("Load book").apply {
        font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
        addActionListener {
            // функция launch создает корутину (в данном случае, в глобальном скоупе)
            scope.launch {
                isEnabled = false
                infoArea.text = "Loading book information...\n"
                val book = loadBook()
                println("Loaded: $book")
                infoArea.append("Book: ${book.title}\nYear: ${book.year}\nGenre: ${book.genre}\n")
                infoArea.append("Loading author information...\n")
                val author = loadAuthor(book)
                println("Loaded: $author")
                infoArea.append("Author: ${author.name}\nBiography: ${author.bio}\n")
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
        thread {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerLabel.text = String.format("Time: %02d:%02d", minutes, seconds)
                Thread.sleep(1000)
                totalSeconds++
            }
        }
    }

    // suspend - функция, которая может прерваться/приостановиться
    private suspend fun loadBook(): Book {
        delay(3000)
        return Book("1984", 1949, "Dystopia")
    }

    private suspend fun loadAuthor(book: Book): Author {
        delay(3000)
        return Author("George Orwell", "British writer and journalist")
    }
}