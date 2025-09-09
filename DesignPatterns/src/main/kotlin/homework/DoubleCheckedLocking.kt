package homework

/*
    В текущем коде DatabaseConnection реализован как обычный Singleton, но он не является потокобезопасным.
    В многопоточной среде возможна ситуация, когда два потока одновременно вызовут getInstance(),
    что может привести к созданию нескольких экземпляров.

    Ваша задача — реализовать Double-Checked Locking, чтобы обеспечить потокобезопасность и
    избежать ненужной синхронизации после первой инициализации.
 */

class DatabaseConnection private constructor() {

    companion object {
        private var instance: DatabaseConnection? = null

        fun getInstance(): DatabaseConnection {
            if (instance == null) {
                instance = DatabaseConnection()
            }
            return instance!!
        }
    }

    fun query(sql: String): String {
        return "Результат запроса: $sql"
    }
}