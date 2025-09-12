package users

import command.Command
import command.Invoker
import java.util.concurrent.LinkedBlockingDeque
import kotlin.concurrent.thread

object UsersInvoker : Invoker {

    private val commands = LinkedBlockingDeque<Command>()

    init {
        thread {
            while (true) {
                // метод take() забирает задачу из очереди и удаляет ее
                // если очередь пуста, поток блокируется до того, как придет новая задача
                commands.take().execute()
            }
        }
    }

    override fun addCommand(command: Command) {
        commands.add(command)
    }
}