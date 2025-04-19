# Práctica 1: Introducción a Kotlin

Esta práctica tiene como objetivo familiarizarse con el lenguaje de programación **Kotlin** a través de ejercicios básicos de lógica de programación. Se trabajará con estructuras condicionales, bucles, generación de números aleatorios y operaciones matemáticas.

## Ejercicios

### 1️⃣ Evaluación de empleados

Una empresa realiza evaluaciones semestrales a sus empleados, otorgando puntuaciones entre **0 y 10**, lo que se traduce en beneficios económicos.  
El dinero extra se calcula con la fórmula:

> **Dinero = Salario Mensual × (Puntuación / 10)**

📌 **Niveles de rendimiento:**

| Nivel       | Puntuación  |
|------------ |------------ |
| Inaceptable | 0 a 3       |
| Aceptable   | 4 a 6       |
| Meritorio   | 7 a 10      |

📌 **Tareas del programa:**  
- Leer la puntuación y el salario mensual del usuario.  
- Determinar el nivel de rendimiento según la puntuación.  
- Calcular e imprimir el bono correspondiente.  

📍 **Ejemplo:**  
**Entrada:** Salario = `10,000`, Puntuación = `8`  
**Salida:** Nivel de Rendimiento: `Meritorio`, Dinero Recibido: `$8,000`

🔗 [Código desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Evaluacion%20de%20empleados.kt)

---

### 2️⃣ Piedra, papel o tijeras

Se implementará el clásico juego **Piedra, Papel o Tijeras**, donde el usuario jugará contra la computadora.

📌 **Tareas del programa:**  
- Generar una opción aleatoria para la computadora (`Piedra`, `Papel` o `Tijeras`).  
- Pedir al usuario que ingrese su elección.  
- Determinar y mostrar el resultado: **Gana, Pierde o Empata**.  

📍 **Ejemplo:**  
**Entrada:** Usuario = `Piedra`, Computadora = `Tijeras`  
**Salida:** `Ganaste!!!`  

🔗 [Código desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Piedra%2C%20papel%20o%20tijeras.kt)

---

### 3️⃣ Calculadora elemental

Se desarrollará una calculadora con las **cuatro operaciones básicas**:

📌 **Menú de la Calculadora**

#### ==== Menú ====  
1. Suma  
2. Resta  
3. Multiplicación  
4. División  
5. Salir  

📌 **Tareas del programa:**  
- Mostrar un **menú de opciones**.  
- Solicitar al usuario dos números y la operación deseada.  
- Validar que la opción ingresada sea válida.  
- Calcular y mostrar el resultado.  
- Incluir una opción para **salir del programa**.  

📍 **Ejemplo:**  
**Entrada:** Opción = `1 (Suma)`, Números = `4 y 6`  
**Salida:** `Resultado: 10`

🔗 [Código desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Calculadora%20elemental.kt)

---

### 4️⃣ Adivina el número

El programa genera un número aleatorio entre **1 y 30** y el usuario debe adivinarlo.

📌 **Tareas del programa:**  
- Generar un número secreto aleatorio entre `1 y 30`.  
- Permitir hasta **5 intentos** para adivinar.  
- Dar pistas indicando si el número es **mayor o menor** que el ingresado.  
- Si el usuario acierta, mostrar un **mensaje de felicitaciones**.  
- Si se agotan los intentos, mostrar **Game Over**.  

📍 **Ejemplo:**  
**Entrada:** Usuario = `15`, Número Secreto = `20`  
**Salida:** `El número es mayor. Intenta de nuevo.`

🔗 [Código desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Adivina%20el%20numero.kt)

---