package users

class Administrator {
    private val repository = UserRepository.getInstance("qwerty")

    fun work() {
        val operationCodes = Operation.entries
        while (true) {
            print("Enter an operation: ")
            for ((index, operation) in operationCodes.withIndex()) {
                print("$index - ${operation.title}")
                if (index == operationCodes.lastIndex) {
                    print(": ")
                } else {
                    print(", ")
                }
            }
            val operation = operationCodes[readln().toInt()]
            when (operation) {
                Operation.EXIT -> {
                    repository.saveChanges()
                    break
                }
                Operation.ADD -> addUser()
                Operation.DELETE -> deleteUser()
            }
        }
    }

    private fun addUser() {
        print("Enter an firstname: ")
        val firstname = readln()
        print("Enter an lastname: ")
        val lastname = readln()
        print("Enter an age: ")
        val age = readln().toInt()
        repository.addUser(firstname, lastname, age)
    }

    private fun deleteUser() {
        print("Enter user id: ")
        repository.removeUser(readln().toInt())
    }
}