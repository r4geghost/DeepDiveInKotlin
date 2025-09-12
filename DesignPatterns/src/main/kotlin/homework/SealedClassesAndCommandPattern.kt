package homework

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/*
    В текущем коде управление устройствами (Light, TV, AirConditioner) выполняется напрямую, без единого интерфейса.
    Это делает код нерасширяемым и неудобным для добавления новых функций.

    Ваша задача — реализовать паттерн "Команда", чтобы управление устройствами осуществлялось
    через универсальный интерфейс команд. Пульт (RemoteControl) должен принимать команды и выполнять их в фоне,
    обрабатывая их в порядке поступления.
 */

fun interface Command {
    fun execute()
}

interface Invoker<T : Command> {
    fun executeCommand(command: T)
}

class RemoteControl : Invoker<DeviceCommand> {
    private val commands = LinkedBlockingQueue<DeviceCommand>()

    init {
        thread {
            while (true) {
                commands.take().execute()
            }
        }
    }

    override fun executeCommand(command: DeviceCommand) {
        commands.add(command)
    }
}

sealed interface DeviceCommand : Command {
    data class LightOnCommand(val light: Light) : DeviceCommand {
        override fun execute() = light.turnOn()
    }

    data class LightOffCommand(val light: Light) : DeviceCommand {
        override fun execute() = light.turnOff()
    }

    data class TVOnCommand(val tv: TV) : DeviceCommand {
        override fun execute() = tv.turnOn()
    }

    data class TVOffCommand(val tv: TV) : DeviceCommand {
        override fun execute() = tv.turnOff()
    }

    data class TVChangeChannelCommand(val tv: TV, val channel: Int) : DeviceCommand {
        override fun execute() = tv.changeChannel(channel)
    }

    data class AirConditionerOnCommand(val ac: AirConditioner) : DeviceCommand {
        override fun execute() = ac.turnOn()
    }

    data class AirConditionerOffCommand(val ac: AirConditioner) : DeviceCommand {
        override fun execute() = ac.turnOff()
    }

    data class AirConditionerSetTempCommand(val ac: AirConditioner, val temp: Int) : DeviceCommand {
        override fun execute() = ac.setTemperature(temp)
    }
}

class Light {
    fun turnOn() = println("Свет включен")
    fun turnOff() = println("Свет выключен")
}

class TV {
    fun turnOn() = println("Телевизор включен")
    fun turnOff() = println("Телевизор выключен")
    fun changeChannel(channel: Int) = println("Канал переключен на $channel")
}

class AirConditioner {
    fun turnOn() = println("Кондиционер включен")
    fun turnOff() = println("Кондиционер выключен")
    fun setTemperature(temp: Int) = println("Температура установлена на $temp°C")
}

fun runCommandTest() {
    val light = Light()
    val tv = TV()
    val ac = AirConditioner()
    val remote = RemoteControl()

    remote.executeCommand(DeviceCommand.LightOnCommand(light))
    remote.executeCommand(DeviceCommand.TVOnCommand(tv))
    remote.executeCommand(DeviceCommand.TVChangeChannelCommand(tv, 5))
    remote.executeCommand(DeviceCommand.AirConditionerOnCommand(ac))
    remote.executeCommand(DeviceCommand.AirConditionerSetTempCommand(ac, 22))
    remote.executeCommand(DeviceCommand.LightOffCommand(light))
    remote.executeCommand(DeviceCommand.TVOffCommand(tv))
    remote.executeCommand(DeviceCommand.AirConditionerOffCommand(ac))
}

fun main() {
    runCommandTest()
}