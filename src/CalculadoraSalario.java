public class CalculadoraSalario {

    public static double calcularSalario(int categoria, double horasTrabajadas) {
        // Primero defino un if para cuando los valores son menores que 0 para que no se
        // pueda calcular el salario
        if (categoria < 0 || horasTrabajadas < 0) {
            return -1.0;
        }

        double tarifaHora;

        // estructura switch para definir la tarifa por hora según la categoría del empleado
        switch (categoria) {
            case 1:
                tarifaHora = 12000;
                break;
            case 2:
                tarifaHora = 17000;
                break;
            case 3:
                tarifaHora = 22000;

                break;
            default:
                return -1.0;

        }

        //calculamos la cantidad de horas extra y el salario total según la tarifa por hora y las horas trabajadas
        if (horasTrabajadas > 40) {
            double salario = (40 * tarifaHora) + ((horasTrabajadas - 40) * (tarifaHora * 1.25));
            return salario;
        } else {
            double salario = horasTrabajadas * tarifaHora;
            return salario;
        }
    }
}