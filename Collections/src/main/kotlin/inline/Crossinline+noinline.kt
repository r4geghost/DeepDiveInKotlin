package inline

import kotlin.concurrent.thread

fun main() {
    executeAsync {
        println("Thread ${Thread.currentThread().name}, Hello World")
    }
    executeAsync {
        println("Thread ${Thread.currentThread().name}, Hello World")
    }
    executeCommand(
        command = { println("Command is working!") },
        task = { println("Task is working!") }
    )
}

/*
    private inline fun executeAsync(task: () -> Unit) {
        thread {
            // Can't inline 'task' here: it may contain non-local returns.
            // Add 'crossinline' modifier to parameter declaration 'task'
            task()
        }
    }
 */

// crossinline означает, что функция будет иметь другой контекст исполнения
// нельзя использовать non local return + нельзя вызывать suspend функции внутри корутины
private inline fun executeAsync(crossinline task: () -> Unit) {
    thread {
        task()
    }
}

/* noinline - если нужно, чтобы компилятор инлайнил определенное лямбда-выражение
     (например, если нужно не вызывать эту лямбду, а использовать ее как объект)
     имеет смысл, только если функция принимает несколько лямбда-выражений!
 */
private inline fun executeCommand(
    noinline command: () -> Unit,
    task: () -> Unit
) {
    Invoker.addCommand(command) // передаем как объект!
    task()
}

object Invoker {
    private val commands = mutableListOf<() -> Unit>()

    fun addCommand(command: () -> Unit) {
        commands.add(command)
    }
}
