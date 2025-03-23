# Programación Para Dispositivos Móviles

📅 Ciclo Académico: 2025 - I  
🎓 Semestre: VI  
👨‍🏫 Profesor: Josue Miguel Flores Parra  
✍ Autor: Rodrigo Emerson Infanzón Acosta  

---

## 📌 Práctica 1: Introducción a Kotlin  

🔗 [Repositorio de la práctica](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/tree/main/Practica%201%20-%20Introduccion%20a%20Kotlin)  

Esta práctica tiene como objetivo familiarizarse con el lenguaje de programación **Kotlin** a través de ejercicios básicos de lógica de programación. Se trabajará con estructuras condicionales, bucles, generación de números aleatorios y operaciones matemáticas.


## 🔹 Ejercicios  

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

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Evaluacion%20de%20empleados.kt)  

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

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Piedra%2C%20papel%20o%20tijeras.kt)  

---

### 3️⃣ Calculadora elemental  
Se desarrollará una calculadora con las **cuatro operaciones básicas**:  

### 📌 Menú de la Calculadora  

#### ==== Menú ====  
1. Suma  
2. Resta
3.  Multiplicación
4.  División
5.  Salir  

📌 **Tareas del programa:**  
- Mostrar un **menú de opciones**.  
- Solicitar al usuario dos números y la operación deseada.  
- Validar que la opción ingresada sea válida.  
- Calcular y mostrar el resultado.  
- Incluir una opción para **salir del programa**.  

📍 **Ejemplo:**  
**Entrada:** Opción = `1 (Suma)`, Números = `4 y 6`  
**Salida:** `Resultado: 10`  

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Calculadora%20elemental.kt)  

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

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%201%20-%20Introduccion%20a%20Kotlin/Adivina%20el%20numero.kt)  

---

# 📌 Práctica 2: Programación Orientada a Objetos en Kotlin  

🔗 [Repositorio de la práctica](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/tree/main/Practica%202%20-%20Programacion%20Orientada%20a%20Objetos%20Kotlin)  

Esta práctica tiene como objetivo aplicar los conceptos de **Programación Orientada a Objetos (POO)** en **Kotlin**, incluyendo el uso de **clases, herencia, interfaces y encapsulamiento**.  

## 🔹 Ejercicios  

### 1️⃣ Cuenta Bancaria  
📌 **Descripción:**  
Se debe implementar una clase `CuentaBancaria` con un saldo y un límite de retiro.  

📍 **Requisitos:**  
- Métodos `set` y `get` para el saldo.  
- Validar los datos al establecer el saldo.  
- Método `retirar` que valide el límite de retiro.  

📍 **Ejemplo de uso:**  

```bash
> Ingrese saldo inicial: 5000
> Ingrese límite de retiro: 1000
> Ingrese monto a retirar: 1200
No puedes retirar más del límite permitido.
> Ingrese monto a retirar: 800
Retiro exitoso. Saldo restante: 4200
```

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%202%20-%20Programacion%20Orientada%20a%20Objetos%20Kotlin/Clase%20cuenta%20bancaria.kt) 

---

### 2️⃣ Producto
📌 **Descripción:**
Se debe implementar una clase `Producto` con un precio y un descuento aplicable.

📍 **Requisitos:**

- Métodos `set` y `get` para establecer y obtener el precio y el descuento.
- Método para calcular el `precio final` después de aplicar el descuento.
- Validar los datos al establecer el precio y el descuento.

📍 **Ejemplo de uso:** 

```bash
> Ingrese precio del producto: 100
> Ingrese porcentaje de descuento: 15
Precio final con descuento: 85.0
```

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%202%20-%20Programacion%20Orientada%20a%20Objetos%20Kotlin/Clase%20producto.kt)

---

### 3️⃣ Figuras
📌 **Descripción:**
Se debe crear una clase abstracta Shape con métodos para calcular el área y el perímetro.

📍 **Requisitos:**

- Clase `Shape` con propiedades área y perímetro.
- Métodos abstractos para `calcular el área` y el `perímetro`.
- Subclases `Cuadrado`, `Círculo` y `Rectángulo` que implementen los cálculos.

📍 **Ejemplo de uso:**

```bash
> Crear cuadrado con lado 4
Área: 16, Perímetro: 16

> Crear círculo con radio 3
Área: 28.27, Perímetro: 18.
```
🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%202%20-%20Programacion%20Orientada%20a%20Objetos%20Kotlin/Clase%20figuras.kt)

---

### 4️⃣ Sistema de Gestión de Biblioteca
📌 **Descripción:**
Se debe diseñar un sistema de biblioteca con las siguientes clases e interfaces:

📍 **Estructura:**

- Material: Clase abstracta con propiedades como `título`, `autor`, `añoPublicacion`.
- Libro: Subclase con `género`, `númeroPáginas`, `mostrarDetalles()`.
- Revista: Subclase con `ISSN`, `volumen`, `editorial`, `mostrarDetalles()`.
- Usuario: Data class con `nombre`, `apellido`, `edad`.
- IBiblioteca: Interfaz con métodos `registrarMaterial()`, `préstamo()`, `devolución()`.
- Biblioteca: Clase que gestiona los préstamos y devoluciones, implementa la interfaz.

📍 **Ejemplo de uso:**

```bash
> Registrar usuario: Juan Pérez, 25 años
Usuario registrado con éxito.
> Registrar libro: "El Quijote", Miguel de Cervantes, 1605
Material registrado con éxito.
> Prestar libro a Juan Pérez
Préstamo realizado correctamente.
> Devolver libro
Devolución exitosa. El libro está disponible nuevamente.
```

---
---
