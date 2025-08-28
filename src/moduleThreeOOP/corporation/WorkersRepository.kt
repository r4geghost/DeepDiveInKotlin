package moduleThreeOOP.corporation

import java.io.File

class WorkersRepository {

    private val fileWorkers = File("workers.txt")

    fun registerNewEmployee(worker: Worker) = saveWorkerToFile(worker)

    private fun saveWorkerToFile(worker: Worker) {
        fileWorkers.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.getSalary()}%${worker.position}\n")
    }

    fun loadAllEmployees(): MutableList<Worker> {
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
        fileWorkers.writeText("")
        for (employee in loadAllEmployees()) {
            if (employee.id != id) {
                saveWorkerToFile(employee)
            }
        }
    }

    fun changeSalary(id: Int, salary: Int) {
        fileWorkers.writeText("")
        for (employee in loadAllEmployees()) {
            if (employee.id == id) {
                employee.setSalary(salary)
            }
            saveWorkerToFile(employee)
        }
    }
}