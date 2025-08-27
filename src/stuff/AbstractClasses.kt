package stuff

import kotlin.math.PI
import kotlin.math.round

abstract class Shape(protected val name: String) {
    abstract fun area(): Double

    abstract fun perimeter(): Double

    protected fun roundToTwoDecimals(value: Double): Double {
        return round(value * 100) / 100
    }
}

class Circle(private val radius: Double) : Shape("Круг") {
    override fun area(): Double {
        return roundToTwoDecimals(PI * radius * radius)
    }

    override fun perimeter(): Double {
        return roundToTwoDecimals(2 * PI * radius)
    }
}

class Rectangle(private val width: Double, private val height: Double) : Shape("Прямоугольник") {
    override fun area(): Double {
        return roundToTwoDecimals(width * height)
    }

    override fun perimeter(): Double {
        return roundToTwoDecimals(2 * (width + height))
    }
}