package com.example.configuradordepedidosdecomida

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

class ResumenPedidoFragment : Fragment(R.layout.fragment_resumen_pedido) {
    // Metodo llamado cuando la vista del fragmento ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de los elementos de la interfaz
        val txtComida = view.findViewById<TextView>(R.id.txtComida)
        val txtExtras = view.findViewById<TextView>(R.id.txtExtras)
        val btnEditarPedido = view.findViewById<Button>(R.id.btnEditarPedido)
        val btnConfirmarPedido = view.findViewById<Button>(R.id.btnConfirmarPedido)

        // Uso de arguments?.getString(...) para mostrar la comida y extras
        val comidaSeleccionada = arguments?.getString("comidaSeleccionada")
        val extrasSeleccionados = arguments?.getString("extrasSeleccionados")

        // Actualización de los TextViews con los datos del pedido
        txtComida.text = getString(R.string.comida, comidaSeleccionada)
        txtExtras.text = getString(R.string.extras, extrasSeleccionados)

        // Configuración del botón Confirmar
        btnConfirmarPedido.setOnClickListener {
            // Muestra un mensaje de confirmación
            Toast.makeText(context, R.string.mensaje_confirmacion, Toast.LENGTH_LONG).show()
            // Paso de datos hacia adelante con navigate() - Navegación simple sin datos
            findNavController().navigate(R.id.action_resumenPedidoFragment_to_inicioFragment2)
        }

        // Configuración del botón Editar
        btnEditarPedido.setOnClickListener {
            // Uso de objeto Bundle para paso de datos - Creando un nuevo Bundle con la comida actual
            val bundle = Bundle().apply {
                putString("comidaSeleccionada", comidaSeleccionada)
            }
            // Comunicación de regreso con setFragmentResult() - Enviando datos al fragmento anterior
            setFragmentResult("requestKey", bundle)
            // Uso de popBackStack() para volver a otro fragmento
            findNavController().popBackStack()
            //findNavController().popBackStack() // descomentar si se quiere volver de ResumenFragment a SeleccionComidaFragment
        }
    }
}