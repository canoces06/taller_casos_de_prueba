import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculadoraSalarioTest {
    
    //con esta prueba verifico que el salario calculado para la categoría 1 con 40 horas trabajadas sea correcto
    @Test
    void testHorasExtraCat1() {
        double resultado = CalculadoraSalario.calcularSalario(1, 50);
        assertEquals(630000, resultado);

    }

    //con esta prueba verifico que el salario calculado para la categoría 2 con 45 horas trabajadas sea correcto
    @Test
    void testHorasExtraCat2() {
        double resultado = CalculadoraSalario.calcularSalario(2, 45);
        assertEquals(786250, resultado);
    }

    //con esta prueba verifico que el salario calculado para la categoría 3 con 54 horas trabajadas sea correcto
    @Test
    void testHorasExtraCat3() {
        double resultado = CalculadoraSalario.calcularSalario(3, 54);
        assertEquals(1265000, resultado);
    }

    // PRUEBAS DE CAMINO BÁSICO Y CONDICIÓN

    @Test
    void testCaminoHorasNegativas() {
        //ponemos una cantidad de horas negativas para verificar que el resultado sea -1.0
        double resultado = CalculadoraSalario.calcularSalario(1, -4);
        assertEquals(-1.0, resultado);
    }

    @Test
    void testCaminoCategoriaInvalida() {
        // Categoría inexistente (ej 4)
        double resultado = CalculadoraSalario.calcularSalario(4, 30.0);
        assertEquals(-1.0, resultado);
    }

    @Test
    void testCaminoHorasNormalesCategoria2() {
        // Horas normales (<= 40) en Categoría 2 (17.000)
        // 30 * 17.000 = 510.000
        double resultado = CalculadoraSalario.calcularSalario(2, 30.0);
        assertEquals(510000.0, resultado);
    }

    //PRUEBAS DE PARTICIÓN DE EQUIVALENCIA

    @Test
    void testEquivalenciaCategoria3HorasExtras() {
        // Clase válida con horas extras en Categoría 3 (22.000)
        // 40h * 22.000 = 880.000
        // 10h extras * (22.000 * 1.25 = 27.500) = 275.000
        // Total = 1.155.000
        double resultado = CalculadoraSalario.calcularSalario(3, 50.0);
        assertEquals(1155000.0, resultado);
    }

    @Test
    void testEquivalenciaCategoriaMenorCero() {
        // Clase inválida: Categoría negativa
        double resultado = CalculadoraSalario.calcularSalario(-1, 20.0);
        assertEquals(-1.0, resultado);
    }

    //ANÁLISIS DE VALORES LÍMITE (AVL)

    @Test
    void testLimiteCeroHoras() {
        // Justo en el límite inferior válido de horas
        double resultado = CalculadoraSalario.calcularSalario(1, 0.0);
        assertEquals(0.0, resultado);
    }

    @Test
    void testLimiteMaximoHorasNormales() {
        // Límite exacto de 40 horas sin generar extras (Categoría 1: 12.000)
        // 40 * 12.000 = 480.000
        double resultado = CalculadoraSalario.calcularSalario(1, 40.0);
        assertEquals(480000.0, resultado);
    }

    @Test
    void testLimiteMinimoHorasExtras() {
        // Límite donde apenas comienza a contar 1 hora extra (Categoría 1)
        // 40h (480.000) + 1h extra (15.000) = 495.000
        double resultado = CalculadoraSalario.calcularSalario(1, 41.0);
        assertEquals(495000.0, resultado);
    }
}
