package inside

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JTextArea

class Display {
    val textField = JTextArea()

    val button = JButton().apply {
        addActionListener(InnerClickListener())
    }

    // создаем вложенный (nested) класс
    // несмотря на то, что класс ClickListener внутри Display, мы не можем обращаться к свойствам класса Display
    private class ClickListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            // textField.text = "Clicked" // ошибка!
        }
    }

    // создаем внутренний (inner) класс = можем обращаться к свойствам наружного класса Display
    private inner class InnerClickListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            textField.text = "Clicked" // ошибки нет!
        }
    }

    // анонимный класс является внутренним (inner) классом!
    val otherButton = JButton().apply {
        addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent?) {
                textField.text = "Clicked other button!"
            }
        })
    }
}

