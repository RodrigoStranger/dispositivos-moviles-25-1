# Práctica 4: Comunicación entre Actividades

## Ejercicios  

### 1️⃣ Editor de Perfil con Confirmación

📌 **Instrucciones:**

Crear una app que permita llenar un perfil de usuario, mostrar los datos en otra pantalla y confirmar si está correcto.


La aplicación debe tener:


🧩 Actividad 1 – FormularioActivity
- Cuatro campos para: Nombre, Edad, Ciudad, y Correo electrónico.
- Un botón que diga “Continuar”.
- Al presionarlo, se envían los datos a la segunda actividad.


🧩 Actividad 2 – ResumenActivity
- Muestra un resumen de los datos escritos.
- Tiene dos botones: “Confirmar” ( vuelve a la primera pantalla y aparece un Toast que diga “Perfil guardado correctamente) y “Volver a editar” (vuelve a la pantalla anterior para seguir editando información).

📍 **Se debe usar:**
- Envío de datos con Intent (usando una clase Usuario y Parcelable).
- Envío de resultados de vuelta con ActivityResult.
- Guardar los textos escritos si se gira la pantalla usando onSaveInstanceState().

🧠 **Tips útiles:**
- Puedes usar una data class Usuario(val nombre: String, ...) para enviar los datos.
- Muestra los datos con TextView en la segunda pantalla.
- Asegúrate de restaurar los campos en FormularioActivity si la pantalla se rota.

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/tree/main/Practica%204%20-%20Comunicacion%20entre%20Actividades/Editordeperfilconconfirmacion)

---

### 2️⃣ Editor de nota rápida
📌 **Instrucciones:**
Permitir al usuario escribir una nota, enviarla a otra actividad para elegir compartirla o volver a editar.


La aplicación debe tener:


🧩 Actividad 1 – EditorActivity
- Un campo de texto (EditText) para escribir una nota.
- Un botón que diga “Compartir”
- Al presionar, la nota se manda a otra pantalla.


🧩 Actividad 2 - OpcionesActivity
- Muestra la nota recibida.
- Tiene dos botones: “Compartir por correo” (muestra un Toast que diga “Compartido por correo”) y “Editar de nuevo” (vuelve a la pantalla anterior con el texto para seguir escribiendo).

📍 **Se debe usar:**
- Comunicación de datos entre actividades.
- Envío de información de regreso usando ActivityResult.
- Guardar el contenido de la nota si se rota la pantalla con onSaveInstanceState().

🧠 **Tips útiles:**
- Usa Intent.putExtra("nota", texto) para enviar y setResult para regresar.
- Usa onSaveInstanceState() para que el texto no se pierda en caso de rotar el dispositivo.

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/tree/main/Practica%204%20-%20Comunicacion%20entre%20Actividades/Editordenotarapida)