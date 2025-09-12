package users

import command.Command

// sealed — это модификатор, который ограничивает иерархию классов/интерфейсов, указывая фиксированный набор возможных наследников.
// Все реализации должны быть объявлены в том же файле или модуле.
sealed interface AdministratorCommands : Command {
    data class AddUser(
        val repository: UserRepository,
        val firstName: String,
        val lastName: String,
        val age: Int
    ) : AdministratorCommands {
        override fun execute() {
            repository.addUser(firstName, lastName, age)
        }
    }

    data class DeleteUser(
        val repository: UserRepository,
        val id: Int
    ) : AdministratorCommands {
        override fun execute() {
            repository.removeUser(id)
        }
    }

    data class SaveChanges(
        val repository: UserRepository
    ) : AdministratorCommands {
        override fun execute() {
            repository.saveChanges()
        }
    }
}