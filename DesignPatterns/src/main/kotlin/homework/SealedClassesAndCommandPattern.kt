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

    light.turnOn()
    tv.turnOn()
    tv.changeChannel(5)
    ac.turnOn()
    ac.setTemperature(22)
    light.turnOff()
    tv.turnOff()
    ac.turnOff()
}

fun main() {
    runCommandTest()
}