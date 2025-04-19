package com.example.configuradordepedidosdecomida

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SeleccionExtrasFragment : Fragment(R.layout.fragment_seleccion_extras) {
    // Metodo llamado cuando la vista del fragmento ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de los elementos de la interfaz
        val radioGroupExtras = view.findViewById<RadioGroup>(R.id.radioGroupExtras)
        val btnSiguiente = view.findViewById<Button>(R.id.btnSiguiente)

        // Configuración del botón Siguiente
        btnSiguiente.setOnClickListener {
            // Obtiene el extra seleccionado basado en el RadioButton seleccionado
            val extrasSeleccionados = when (radioGroupExtras.checkedRadioButtonId) {
                R.id.radioGaseosa -> getString(R.string.gaseosa)
                R.id.radioEnsalada -> getString(R.string.ensalada)
                R.id.radioPostre -> getString(R.string.postre)
                R.id.radioPapasFritas -> getString(R.string.papas_fritas)
                R.id.radioNingunExtra -> getString(R.string.ningun_extra)
                else -> null
            }

            // Verifica si se ha seleccionado un extra
            if (extrasSeleccionados != null) {
                // Uso de objeto Bundle para paso de datos - Agregando info de extras al Bundle existente
                val bundle = Bundle().apply {
                    // Uso de arguments?.getString(...) para obtener datos del fragmento anterior
                    putString("comidaSeleccionada", arguments?.getString("comidaSeleccionada"))
                    putString("extrasSeleccionados", extrasSeleccionados)
                }
                // Paso de datos hacia adelante con navigate() - Navegando al siguiente fragmento con datos
                findNavController().navigate(R.id.action_seleccionExtrasFragment_to_resumenPedidoFragment2, bundle)
            } else {
                // Muestra un mensaje de error si no se ha seleccionado ningún extra
                Toast.makeText(context, R.string.Error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}