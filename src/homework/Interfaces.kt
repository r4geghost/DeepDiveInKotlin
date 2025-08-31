package homework

fun main() {
    val encryptor = Encryptor()
    println(encryptor.process("sensitive data"))
    println(encryptor.transform("sensitive data"))
    val compressor = Compressor()
    println(compressor.process("large data"))
    println(compressor.transform("large data"))
    val logger = Logger()
    println(logger.process("log data"))
}


interface Transformable {
    fun transform(data: String): String
}

abstract class DataProcessor(val processorName: String) {
    abstract fun process(data: String): String
}

class Encryptor : DataProcessor("Encryptor"), Transformable {
    override fun process(data: String): String {
        return "Обработчик: $processorName обработал данные"
    }

    override fun transform(data: String): String {
        return "$processorName преобразовал данные: ${"encoded_$data"}"
    }
}

class Compressor : DataProcessor("Compressor"), Transformable {
    override fun process(data: String): String {
        return "Обработчик: $processorName обработал данные"
    }

    override fun transform(data: String): String {
        return "$processorName преобразовал данные: ${"compressed_$data"}"
    }
}

class Logger : DataProcessor("Logger") {
    override fun process(data: String): String {
        return "Обработчик: $processorName обработал данные"
    }
}