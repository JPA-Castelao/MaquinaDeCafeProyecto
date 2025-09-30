import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CodigoEstadosTest {

    @Test
    fun `Estado inicial debe ser Idle`() {
        assertTrue(codigoEstados.EstadoActual is Estados.Idle)
    }

    @Test
    fun `Transición de Idle a EligiendoCafe`() {
        codigoEstados.EstadoActual = Estados.Idle()
        codigoEstados.EstadoActual = Estados.EligiendoCafe
        assertTrue(codigoEstados.EstadoActual is Estados.EligiendoCafe)
    }

    @Test
    fun `Transición de EligiendoCafe a HaciendoCafe con opción válida`() {
        val tipoCafe = 2
        codigoEstados.EstadoActual = Estados.HaciendoCafe(tipoCafe)
        assertTrue(codigoEstados.EstadoActual is Estados.HaciendoCafe)
        assertEquals(tipoCafe, (codigoEstados.EstadoActual as Estados.HaciendoCafe).tipoCafe)
    }

    @Test
    fun `Transición de EligiendoCafe a Error con opción inválida`() {
        val tipoCafe = 0
        codigoEstados.EstadoActual = Estados.Error("Elección de café no válida: $tipoCafe")
        assertTrue(codigoEstados.EstadoActual is Estados.Error)
    }

    @Test
    fun `Transición de HaciendoCafe a SirviendoCafe`() {
        val tipoCafe = 1
        val cafeServido = "Preparando un café Normal espere..."
        codigoEstados.EstadoActual = Estados.SirviendoCafe(cafeServido)
        assertTrue(codigoEstados.EstadoActual is Estados.SirviendoCafe)
        assertEquals(cafeServido, (codigoEstados.EstadoActual as Estados.SirviendoCafe).tipo)
    }

    @Test
    fun `Transición a Apagado`() {
        codigoEstados.EstadoActual = Estados.Apagado
        assertTrue(codigoEstados.EstadoActual is Estados.Apagado)
    }
}
