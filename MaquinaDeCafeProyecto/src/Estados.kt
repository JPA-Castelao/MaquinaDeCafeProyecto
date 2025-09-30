
sealed class Estados {

    class Idle : Estados() {
        init {
            println("-> Estado Neutro (Pulsa Enter para Empezar)")
            println("-> Estado Neutro (Pulsa f para salir)")
        }
    }


    data class HaciendoCafe(val tipoCafe: Int) : Estados()

    object EligiendoCafe : Estados() {
        init {
            println("-> Esperando instrucciones (Elige tu café)")
        }
    }

    data class SirviendoCafe(val tipo: String) : Estados()
    data class Error(val error: String) : Estados()

    object Apagado:Estados(){

    }
}
