package com.example.configuradordepedidosdecomida

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class InicioFragment : Fragment(R.layout.fragment_inicio) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Paso de datos hacia adelante con navigate() - Navegación simple sin datos
        val btnNuevoPedido = view.findViewById<Button>(R.id.Btn_NuevoPedido)
        btnNuevoPedido.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_seleccionComidaFragment)
        }
    }
}