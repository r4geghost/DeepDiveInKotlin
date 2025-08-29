package moduleThreeOOP.corporation

import java.io.File

// паттерн singleton (работает только с пустым конструктором)
object WorkersRepository {

    private val fileWorkers = File("workers.txt")

    val workers = loadAllEmployees()

    fun registerNewEmployee(worker: Worker) = workers.add(worker)

    fun saveChanges() {
        val content = StringBuilder()
        for (worker in workers) {
            content.append("${worker.id}%${worker.name}%${worker.age}%${worker.getSalary()}%${worker.position}\n")
        }
        fileWorkers.writeText(content.toString())
    }

    private fun loadAllEmployees(): MutableList<Worker> {
        val employees = mutableListOf<Worker>()
        if (!fileWorkers.exists()) fileWorkers.createNewFile()  // если файла нет, то он создастся
        val content = fileWorkers.readText().trim()
        if (content.isEmpty()) return employees
        val employeesAsText = content.split("\n")
        for (employeeAsText in employeesAsText) {
            val properties = employeeAsText.split("%")
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val salary = properties[3].toInt()
            val positionAsText = properties.last()
            val position = Position.valueOf(positionAsText)
            val worker = when (position) {
                Position.DIRECTOR -> Director(id, name, age, salary)
                Position.ACCOUNTANT -> Accountant(id, name, age, salary)
                Position.ASSISTANT -> Assistant(id, name, age, salary)
                Position.CONSULTANT -> Consultant(id, name, age, salary)
            }
            employees.add(worker)
        }
        return employees
    }

    fun fireEmployee(id: Int) {
        for (worker in workers) {
            if (worker.id == id) {
                workers.remove(worker)
                break
            }
        }
    }

    fun changeSalary(id: Int, salary: Int) {
        for (worker in workers) {
            if (worker.id == id) {
                worker.setSalary(salary)
            }
        }
    }
}