package homework

// Task.kt
class TaskForTest(
    val id: Int,
    val title: String,
    val description: String,
    val assignedTo: String,
    val status: String,
    val priority: String
) {
    fun printTaskInfo() {
        println("Задача #$id: $title")
        println("Описание: $description")
        println("Назначена: $assignedTo")
        println("Статус: $status")
        println("Приоритет: $priority")
        println()
    }

    fun copy(
        title: String = this.title,
        description: String = this.description,
        assignedTo: String = this.assignedTo,
        status: String = this.status,
        priority: String = this.priority
    ): TaskForTest {
        return TaskForTest(this.id, title, description, assignedTo, status, priority)
    }
}

// Employee.kt
class Employee(val id: Int, val name: String) {
    private val _taskForTests = mutableListOf<TaskForTest>()
    val tasks
        get() = _taskForTests.toList()

    private val _archivedTaskForTests = mutableListOf<TaskForTest>()
    val archivedTasks
        get() = _archivedTaskForTests.toList()

    fun addTask(taskForTest: TaskForTest) {
        _taskForTests.add(taskForTest)
        println("Добавлена задача: ${taskForTest.title} для сотрудника $name.")
    }

    fun removeTask(taskId: Int) {
        val task = _taskForTests.find { it.id == taskId }
        if (task != null) {
            _archivedTaskForTests.add(task)
            _taskForTests.remove(task)
            println("Задача ${task.title} удалена.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskStatus(taskId: Int, newStatus: String) {
        val task = _taskForTests.find { it.id == taskId }
        if (task != null) {
            _archivedTaskForTests.add(task)
            val newTask = task.copy(status = newStatus)
            _taskForTests.remove(task)
            _taskForTests.add(newTask)
            println("Статус задачи ${task.title} изменен на '$newStatus'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun changeTaskAssignee(taskId: Int, newAssignee: String) {
        val task = _taskForTests.find { it.id == taskId }
        if (task != null) {
            _archivedTaskForTests.add(task)
            val newTask = task.copy(assignedTo = newAssignee)
            _taskForTests.remove(task)
            _taskForTests.add(newTask)
            println("Задача ${task.title} переназначена на $newAssignee.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun updateTaskPriority(taskId: Int, newPriority: String) {
        val task = _taskForTests.find { it.id == taskId }
        if (task != null) {
            _archivedTaskForTests.add(task)
            val newTask = task.copy(priority = newPriority)
            _taskForTests.remove(task)
            _taskForTests.add(newTask)
            println("Приоритет задачи ${task.title} изменен на '$newPriority'.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun modifyTaskDetails(taskId: Int, newTitle: String, newDescription: String) {
        val task = _taskForTests.find { it.id == taskId }
        if (task != null) {
            _archivedTaskForTests.add(task)
            val newTask = task.copy(description = newDescription, title = newTitle)
            _taskForTests.remove(task)
            _taskForTests.add(newTask)
            println("Детали задачи ${task.id} обновлены.")
        } else {
            println("Задача с ID $taskId не найдена.")
        }
    }

    fun printTasks() {
        println("Список задач для сотрудника $name:")
        _taskForTests.forEach { it.printTaskInfo() }
    }
}

// Project.kt
class ProjectForTest(val name: String) {
    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
        println("Сотрудник ${employee.name} добавлен в проект '$name'.")
    }

    fun assignTaskToEmployee(employeeId: Int, taskForTest: TaskForTest) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.addTask(taskForTest)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskStatus(employeeId: Int, taskId: Int, newStatus: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskStatus(taskId, newStatus)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun changeEmployeeTaskAssignee(employeeId: Int, taskId: Int, newAssignee: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.changeTaskAssignee(taskId, newAssignee)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun updateEmployeeTaskPriority(employeeId: Int, taskId: Int, newPriority: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.updateTaskPriority(taskId, newPriority)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun modifyEmployeeTaskDetails(employeeId: Int, taskId: Int, newTitle: String, newDescription: String) {
        val employee = employees.find { it.id == employeeId }
        if (employee != null) {
            employee.modifyTaskDetails(taskId, newTitle, newDescription)
        } else {
            println("Сотрудник с ID $employeeId не найден.")
        }
    }

    fun printAllTasks() {
        println("Все задачи проекта '$name':")
        employees.forEach { it.printTasks() }
    }
}