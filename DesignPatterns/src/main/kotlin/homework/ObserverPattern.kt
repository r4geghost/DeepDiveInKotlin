package homework

/*
    Описание
    В проекте уже реализован класс UserRepository, который управляет списком
    пользователей и предоставляет методы для их добавления и удаления.

    Сейчас UserRepository не уведомляет другие компоненты о том, что данные изменились.
    Ваша задача — добавить в него поддержку паттерна Observer,
    чтобы другие классы могли подписываться на изменения списка пользователей и получать уведомления.

    Что нужно сделать

        1) Добавить в UserRepository список подписчиков (MutableList<UserLogger>).
        2) Реализовать метод подписки subscribe(logger: UserLogger),
        который добавляет новый объект UserLogger в список подписчиков и сразу уведомляет его (logger)
        о текущем состоянии списка пользователей.
        3) Реализовать метод отписки unsubscribe(logger: UserLogger),
        который удаляет объект UserLogger из списка подписчиков.
        4) Добавить метод уведомления подписчиков notifyObservers(),
        который вызывается после каждого изменения списка пользователей.
        5) Реализовать в классе UserLogger метод onUsersChanged(users: List<String>),
        который выводит в консоль сообщение в формате:
            [LOG] Пользователи обновлены: [Alice, Bob, Tom, Nick]
 */

// Хранилище пользователей
class UserRepository {
    private val users = mutableListOf<String>()

    private val observers: MutableList<UserLogger> = mutableListOf()

    fun addUser(user: String) {
        users.add(user)
        notifyObservers()
    }

    fun removeUser(user: String) {
        users.remove(user)
        notifyObservers()
    }

    fun subscribe(observer: UserLogger) {
        observers.add(observer)
        observer.onUsersChanged(users)
    }

    fun unsubscribe(observer: UserLogger) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onUsersChanged(users) }
    }
}

// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger {
    fun onUsersChanged(users: List<String>) {
        println("[LOG] Пользователи обновлены: $users")
    }
}