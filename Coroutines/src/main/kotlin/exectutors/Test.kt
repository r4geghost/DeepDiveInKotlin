package exectutors

import java.util.concurrent.Executors

/*
1) создание потока ресурсозатратно, занимает много времени и памяти
2) сложность управления жизненным циклом потока

Executor service - создает определенное кол-во потоков, которые берут задачу в работу, пока они свободны.
Если все потоки заняты, задачи кладутся в очередь, и по мере освобождения потоки берут задачи из очереди
 */
fun main() {
    // если сложные вычисления, желательно кол-во потоков = кол-во ядер
    val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    repeat(10_000) {
        executor.execute {
            process(Image(it))
        }
    }
    executor.shutdown()
}

private fun process(image: Image) {
    println("Image $image is processing...")
    Thread.sleep(500)
    println("Image $image processed")
}

data class Image(val id: Int)