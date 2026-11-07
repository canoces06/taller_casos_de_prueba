# Calculadora de Salario

Aplicación Java que calcula el salario semanal de un empleado a partir de su
categoría profesional y de las horas trabajadas, aplicando un recargo del 25 %
a partir de la hora 41.

## Estructura del proyecto

- `src/`: código fuente y pruebas.
  - `CalculadoraSalario.java`: lógica de cálculo del salario.
  - `CalculadoraSalarioTest.java`: pruebas unitarias con JUnit 5.
  - `App.java`: punto de entrada de la aplicación.
- `lib/`: dependencias (`junit-platform-console-standalone-1.14.4.jar`).
- `bin/`: clases compiladas (salida de compilación).

## Reglas de negocio

### Tarifas por categoría

| Categoría | Tarifa por hora |
|-----------|-----------------|
| 1         | 12.000          |
| 2         | 17.000          |
| 3         | 22.000          |

### Horas extra

- Hasta 40 horas se paga la tarifa normal.
- Cada hora por encima de 40 se paga con un recargo del **25 %**.

```
salario = 40 × tarifa + (horas - 40) × tarifa × 1,25    si horas > 40
salario = horas × tarifa                                 si horas ≤ 40
```

### Validaciones

La función devuelve `-1.0` como valor centinela cuando:

- `categoria < 0`
- `horasTrabajadas < 0`
- la categoría no es 1, 2 ni 3

## API

```java
public static double calcularSalario(int categoria, double horasTrabajadas)
```

## Casos de prueba

El conjunto de pruebas cubre tres técnicas de diseño: camino básico y
condición, partición de equivalencia y análisis de valores límite.

### Horas extra

| Test                 | Categoría | Horas | Resultado esperado | Estado |
|----------------------|-----------|-------|--------------------|--------|
| `testHorasExtraCat1` | 1         | 50    | 630.000            | ✅     |
| `testHorasExtraCat2` | 2         | 45    | 786.250            | ✅     |
| `testHorasExtraCat3` | 3         | 54    | 1.265.000          | ✅     |

### Camino básico y condición

| Test                                | Categoría | Horas | Resultado esperado | Estado |
|-------------------------------------|-----------|-------|--------------------|--------|
| `testCaminoHorasNegativas`          | 1         | -4    | -1.0               | ✅     |
| `testCaminoCategoriaInvalida`       | 4         | 30    | -1.0               | ✅     |
| `testCaminoHorasNormalesCategoria2` | 2         | 30    | 510.000            | ✅     |

### Partición de equivalencia

| Test                                    | Categoría | Horas | Resultado esperado | Estado |
|-----------------------------------------|-----------|-------|--------------------|--------|
| `testEquivalenciaCategoria3HorasExtras` | 3         | 50    | 1.155.000          | ✅     |
| `testEquivalenciaCategoriaMenorCero`    | -1        | 20    | -1.0               | ✅     |

### Análisis de valores límite

| Test                            | Categoría | Horas | Resultado esperado | Estado |
|---------------------------------|-----------|-------|--------------------|--------|
| `testLimiteCeroHoras`           | 1         | 0     | 0.0                | ✅     |
| `testLimiteMaximoHorasNormales` | 1         | 40    | 480.000            | ✅     |
| `testLimiteMinimoHorasExtras`   | 1         | 41    | 495.000            | ✅     |

Las 11 pruebas pasan correctamente.

### Desglose de los casos

- **Cat. 1, 50 h:** `40 × 12.000 + 10 × 15.000 = 630.000`
- **Cat. 2, 45 h:** `40 × 17.000 + 5 × 21.250 = 786.250`
- **Cat. 3, 54 h:** `40 × 22.000 + 14 × 27.500 = 1.265.000`
- **Cat. 2, 30 h (sin extras):** `30 × 17.000 = 510.000`
- **Cat. 3, 50 h:** `40 × 22.000 + 10 × 27.500 = 1.155.000`
- **Límite 40 h:** `40 × 12.000 = 480.000` (sin recargo)
- **Límite 41 h:** `40 × 12.000 + 1 × 15.000 = 495.000` (primer recargo)
- **Casos de error:** horas negativas, categoría negativa o categoría inexistente → `-1.0`

## Compilación y ejecución

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

> En Windows es necesario `-encoding UTF-8` porque los fuentes contienen
> caracteres acentuados.

## Ejecución de las pruebas

```bash
javac -encoding UTF-8 -cp lib/junit-platform-console-standalone-1.14.4.jar -d bin src/*.java
java -jar lib/junit-platform-console-standalone-1.14.4.jar --class-path bin --scan-class-path
```
''' mermaid
graph TD
    A[Inicio] --> B{horas < 0 OR categoria < 1}
    B -- Sí --> C[Retorna -1.0]
    B -- No --> D{Evaluar Categoría}
    
    D -- Caso 1 --> E[tarifa = 12000]
    D -- Caso 2 --> F[tarifa = 17000]
    D -- Caso 3 --> G[tarifa = 22000]
    D -- Default --> H[Retorna -1.0]
    
    E --> I{horas > 40}
    F --> I
    G --> I
    
    I -- Sí --> J[Calcula salario con extras]
    I -- No --> K[Calcula salario normal]
    
    J --> L[Retorna Salario]
    K --> L
 '''
    
