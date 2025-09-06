package test

import extentions.myLet

var age: Int? = 20

fun main() {
    // Код ниже не сработает
//    if (age >= 18) {
//        println("You are an adult")
//    } else {
//        println("You will be an adult in ${18 - age} years")
//    }

    // функция let - внутри блока работаем как с ненулабельным объектом
    // если age == null, то блок кода выполнен не будет
    val result: String? = age?.let {
        if (it >= 18) {
            "You are an adult"
        } else {
            "You will be an adult in ${18 - it} years"
        }
    }

    // let можно вызвать у любого объекта, результат = объект любого типа
    result?.myLet {
        println(it)
    }
}