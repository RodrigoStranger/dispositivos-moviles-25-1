package com.example.configuradordepedidosdecomida

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController

class SeleccionComidaFragment : Fragment(R.layout.fragment_seleccion_comida) {
    // Metodo llamado cuando la vista del fragmento ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de los elementos de la interfaz
        val radioGroupComida = view.findViewById<RadioGroup>(R.id.radioGroupComida)
        val btnSiguiente = view.findViewById<Button>(R.id.btnSiguiente)

        // Comunicación de regreso con setFragmentResultListener() - Recibe datos al editar el pedido
        // Este listener se activa cuando el usuario presiona el botón de editar en el ResumenPedidoFragment
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // Uso de arguments?.getString(...) para obtener datos del pedido actual
            val comidaSeleccionada = bundle.getString("comidaSeleccionada")
            if (comidaSeleccionada != null) {
                // Restaura la selección anterior del usuario
                when (comidaSeleccionada) {
                    getString(R.string.pizza) -> radioGroupComida.check(R.id.radioPizza)
                    getString(R.string.hamburguesa) -> radioGroupComida.check(R.id.radioHamburguesa)
                    getString(R.string.tacos) -> radioGroupComida.check(R.id.radioTacos)
                }
            }
        }

        // Configuración del botón Siguiente
        btnSiguiente.setOnClickListener {
            // Obtiene la comida seleccionada basada en el RadioButton seleccionado
            val comidaSeleccionada = when (radioGroupComida.checkedRadioButtonId) {
                R.id.radioPizza -> getString(R.string.pizza)
                R.id.radioHamburguesa -> getString(R.string.hamburguesa)
                R.id.radioTacos -> getString(R.string.tacos)
                else -> null
            }

            // Verifica si se ha seleccionado una comida
            if (comidaSeleccionada != null) {
                // Uso de objeto Bundle para paso de datos - Creando un nuevo Bundle con la comida seleccionada
                val bundle = Bundle().apply {
                    putString("comidaSeleccionada", comidaSeleccionada)
                }
                // Paso de datos hacia adelante con navigate() - Navegando al siguiente fragmento con datos
                findNavController().navigate(R.id.action_seleccionComidaFragment_to_seleccionExtrasFragment2, bundle)
            } else {
                // Muestra un mensaje de error si no se ha seleccionado ninguna comida
                Toast.makeText(context, R.string.Error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}