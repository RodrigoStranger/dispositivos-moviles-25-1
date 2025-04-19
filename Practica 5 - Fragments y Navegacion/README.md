# Práctica 5: Fragments y Navegación

## Ejercicios  

### 1️⃣ Configurador de pedidos de comida

📌 **Instrucciones:**

Permitir al usuario armar un pedido paso a paso y visualizarlo al final.


La aplicación debe tener:


🧩 Fragmento 1 – InicioFragment
- Botón: "Nuevo pedido" en la cual va a navegar hacia SeleccionComidaFragment.

🧩 Fragmento 2 – SeleccionComidaFragment
- Selección de extras (bebida, papas, postre).
- Al hacer clic en “Siguiente”: Se crea un Bundle con el tipo de comida seleccionado y se navega a SeleccionExtrasFragment.

🧩 Fragmento 3 – SeleccionExtrasFragment
- Selección de extras (bebida, papas, postre).
- Al hacer click en "Siguiente": Se agrega la info de los extras al mismo Bundle o se crea uno nuevo con todo y se navega a ResumenPedidoFragment.

🧩 Fragmento 4 – ResumenPedidoFragment
- Usa arguments?.getString(...), etc. para mostrar la comida y extras.
- Tiene dos botones: “Confirmar pedido” en la cual muestra un Toast y vuelve al inicio (navigate()) y "Editar pedido” que usa setFragmentResult() con los datos actuales del pedido. Este llama a popBackStack() para regresar a SeleccionComidaFragment, que los puede recuperar con setFragmentResultListener(...).

📍 **Se debe usar:**
- Paso de datos hacia adelante con navigate().
- Uso de objeto Bundle para paso de datos.
- Comunicación de regreso con setFragmentResult() + setFragmentResultListener().
- Uso de popBackStack() para volver a otro fragmento.
- Uso de Safe Args de manera opcional.

🔗 [Ejercicio desarrollado](https://github.com/RodrigoStranger/dispositivos-moviles-25-1/tree/main/Practica%205%20-%20Fragments%20y%20Navegacion/Configuradordepedidosdecomida)