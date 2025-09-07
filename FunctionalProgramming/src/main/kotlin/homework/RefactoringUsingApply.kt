package homework

import javax.swing.JLabel
import java.awt.Color

/*
    Вам нужно создать и настроить объект типа JLabel (из библиотеки javax.swing) с определёнными свойствами.
    В исходном коде показаны разные способы настройки объекта с использованием различных scope-функций: with, also, let.
    Ваша задача — создать аналогичный объект, используя функцию apply, и увидеть её преимущества в этом сценарии.

    Важно понимать, что результат будет одинаковым при использовании большинства этих функций, и, скорее всего,
    ваша реализация будет работать корректно, какой бы способ вы ни выбрали. Однако цель задания — увидеть,
    что не все функции одинаково удобны для настройки объектов. Например, apply в данном случае лучше всего подходит,
    так как она создана специально для таких задач, когда нужно настроить объект и вернуть его. Она упрощает код,
    избегает повторения имени объекта и делает логику понятной.

    Попробуйте на практике убедиться, что выбор подходящей функции улучшает не только читаемость, но и вашу работу как разработчика.

    Требования:

        1) Создайте объект типа JLabel, настроенный с использованием apply.
        2) Настройте объект так:
            Текст: "Hello, World!"
            Размер шрифта: 16f
            Цвет текста: Color.BLUE.

    Рассмотрите примеры использования других функций, таких как with, also, let, и обратите внимание, почему в этом случае использование apply лучше.
*/

fun main() {
    // Пример 1: Настройка объекта с помощью прямых вызовов
    val labelDirect = JLabel()
    labelDirect.text = "Hello, World!"
    labelDirect.font = labelDirect.font.deriveFont(16f)
    labelDirect.foreground = Color.BLUE

    // Пример 2: Настройка объекта с помощью функции `with`
    val labelWith = JLabel()
    with(labelWith) {
        text = "Hello, World!"
        font = font.deriveFont(16f)
        foreground = Color.BLUE
    }

    // Пример 3: Настройка объекта с помощью функции `also`
    val labelAlso = JLabel().also { label ->
        label.text = "Hello, World!"
        label.font = label.font.deriveFont(16f)
        label.foreground = Color.BLUE
    }

    // Пример 4: Настройка объекта с помощью функции `let`
    val labelLet = JLabel().let { label ->
        label.text = "Hello, World!"
        label.font = label.font.deriveFont(16f)
        label.foreground = Color.BLUE
        label // Возвращаем объект
    }

    // Пример 5: Настройка объекта с помощью функции `apply`
    val labelApply = JLabel().apply {
        text = "Hello, World!"
        font = font.deriveFont(16f)
        foreground = Color.BLUE
    }

    println(labelDirect)
    println(labelWith)
    println(labelAlso)
    println(labelLet)
    println(labelApply)
}