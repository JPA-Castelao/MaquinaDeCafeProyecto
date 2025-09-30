object codigoEstados {
    public var EstadoActual: Estados = Estados.Idle()


    fun hacerCafe() {


        fun elegirCafe(): Int {
            println("\nElige tu café:")
            println("1 - Normal")
            println("2 - Largo")
            println("3 - Normal Doble")
            println("4 - Largo Doble")

            val tipoConversion = readLine()?.toIntOrNull() ?: 0


            return tipoConversion
        }

        fun interfazCafe(cafe: Int): String {
            var stringCafe: String = ""
            when (cafe) {
                1 -> stringCafe = "Normal"
                2 -> stringCafe = "Largo"
                3 -> stringCafe = "Doble"
                4 -> stringCafe = "Doble Largo"
                else -> "Error"

            }
            return ("Preparando un café ${stringCafe} espere...")
        }


        while (true) {
            println("ESTADO ACTUAL= ${EstadoActual::class.simpleName}")

            when (EstadoActual) {

                is Estados.Idle -> {

                    val input = readLine()
                    if (input != "f") {
                        EstadoActual = Estados.EligiendoCafe
                    }else{
                        EstadoActual=Estados.Apagado
                    }
                }

                is Estados.EligiendoCafe -> {
                    var cafe: Int = elegirCafe()

                    if (cafe in 1..4) {
                        EstadoActual = Estados.HaciendoCafe(cafe)
                    } else {
                        EstadoActual = Estados.Error("Elección de café no válida: $cafe")
                    }
                }

                is Estados.HaciendoCafe -> {

                    val tipoCafe: Int = (EstadoActual as Estados.HaciendoCafe).tipoCafe
                    var cafeServido = interfazCafe(tipoCafe)

                    EstadoActual = Estados.SirviendoCafe(cafeServido)
                }

                is Estados.SirviendoCafe -> {
                    var nombreCafe: String = (EstadoActual as Estados.SirviendoCafe).tipo
                    println("\n ${nombreCafe}")
                    println("\n Cafe servido. Retire.")

                    EstadoActual = Estados.Idle()
                }

                is Estados.Error -> {
                    val mensajeError = (EstadoActual as Estados.Error).error

                    println("Hay un error ${mensajeError}")

                    EstadoActual = Estados.Idle()
                }

                is Estados.Apagado -> {
                    println("Apagando...")
                    break
                }


            }


        }


    }
}

