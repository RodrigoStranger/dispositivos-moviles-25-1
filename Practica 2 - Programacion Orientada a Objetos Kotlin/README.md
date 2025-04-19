# Práctica 2: Programación Orientada a Objetos en Kotlin

## Ejercicios

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

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/blob/main/Practica%202%20-%20Programacion%20Orientada%20a%20Objetos%20Kotlin/Sistema%20de%20gestion%20de%20bibliotecas.kt)