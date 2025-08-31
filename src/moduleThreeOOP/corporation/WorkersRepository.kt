package moduleThreeOOP.corporation

import java.io.File

// паттерн singleton (работает только с пустым конструктором)
object WorkersRepository {

    private val fileWorkers = File("_workers.txt")

    // backing field (теневое поле)
    private val _workers = loadAllEmployees()

    val workers
        // возвращаем неизменяемую копию коллекции
        get() = _workers.toList()

    fun registerNewEmployee(worker: Worker) = _workers.add(worker)

    fun saveChanges() {
        val content = StringBuilder()
        for (worker in _workers) {
            content.append("${worker.id}%${worker.name}%${worker.age}%${worker.salary}%${worker.position}\n")
        }
        fileWorkers.writeText(content.toString())
    }

    private fun loadAllEmployees(): MutableSet<Worker> {
        val employees = mutableSetOf<Worker>()
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
        for (worker in _workers) {
            if (worker.id == id) {
                _workers.remove(worker)
                break
            }
        }
    }

    fun changeSalary(id: Int, salary: Int) {
        for (worker in _workers) {
            if (worker.id == id) {
                val newWorker = worker.copy(salary = salary)
                _workers.remove(worker)
                _workers.add(newWorker)
                break
            }
        }
    }

    fun changeAge(id: Int, age: Int) {
        for (worker in _workers) {
            if (worker.id == id) {
                val newWorker = worker.copy(age = age)
                _workers.remove(worker)
                _workers.add(newWorker)
                break
            }
        }
    }

    // возвращает null, если нет объекта указанного типа
    fun findAssistant(): Assistant? {
        for (worker in _workers) {
            if (worker is Assistant) {
                return worker
            }
        }
        return null
    }

    // возвращает null, если нет объекта указанного типа
    fun findDirector(): Director? {
        for (worker in _workers) {
            if (worker is Director) {
                return worker
            }
        }
        return null
    }
}