package callbacks

import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

object Display {

    private val infoArea = JTextArea().apply { isEditable = false }

    private val button = JButton("Load book")

    private val timerLabel = JLabel("Time: 00:00")

    private val topPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(button, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("Book and Author info").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoArea), BorderLayout.CENTER)
        size = Dimension(400, 400)
    }

    fun show() {
        mainFrame.isVisible = true
    }
}