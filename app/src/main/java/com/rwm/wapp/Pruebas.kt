package com.rwm.wapp

var count: Int = 3

fun prueba() {
    /* PRUEBAS */
    // Para variables definiendo un tipo de valor
    var count1: Int = 3
    // Variables sin asiganar tipo (Autoresuelve el tipo)
    var i = 3
    // no se puede cambiar el tipo una ver autoresuelto
    // ejmplo:  i = "hola" -> error
    // Constantes al igual k las variables se puede autoasignar pero este valor ya no se puede cambiar mas
    val MI_NOMBRE: String = "Rula"
    var aaa = MI_NOMBRE.lowercase()
    // CONDICIONES
    if (3 > 5) {
        println("prueba1")
    } else if (5 == 5) {
        println("prueba2")
    } else {
        println("prueba3")
    }
    var answerString: String = if (count == 42) {
        "I have the answer."
    } else if (count > 35) {
        "The answer is close."
    } else {
        "The answer eludes me."
    }
    println(answerString)
    // swich java
    when {
        count > 5 -> println("ghola")
        67 == 5 -> println("juse")
    }
    var answerString2 = when {
        count == 42 -> "I have the answer."
        count > 35 -> "The answer is close."
        else -> "The answer eludes me."
    }
    println(answerString)
    //llamamos a funcion
    val answerString3 = generateAnswerString(42)
    /* FIN PRUEBAS */
}

/* PRUEBAS funciones */
fun generateAnswerString(countThreshold: Int): String {
    val answerString = if (count > countThreshold) {
        "I have the answer."
    } else {
        "The answer eludes me."
    }

    return answerString
}
/* FIN PRUEBAS */