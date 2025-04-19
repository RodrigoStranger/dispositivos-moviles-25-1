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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtComida = view.findViewById<TextView>(R.id.txtComida)
        val txtExtras = view.findViewById<TextView>(R.id.txtExtras)
        val btnEditarPedido = view.findViewById<Button>(R.id.btnEditarPedido)
        val btnConfirmarPedido = view.findViewById<Button>(R.id.btnConfirmarPedido)

        val comidaSeleccionada = arguments?.getString("comidaSeleccionada")
        val extrasSeleccionados = arguments?.getString("extrasSeleccionados")

        txtComida.text = getString(R.string.comida, comidaSeleccionada)
        txtExtras.text = getString(R.string.extras, extrasSeleccionados)

        btnConfirmarPedido.setOnClickListener {
            Toast.makeText(context, R.string.mensaje_confirmacion, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_resumenPedidoFragment_to_inicioFragment2)
        }

        btnEditarPedido.setOnClickListener {
            val bundle = Bundle().apply {
                putString("comidaSeleccionada", comidaSeleccionada)
            }
            setFragmentResult("requestKey", bundle)
            findNavController().popBackStack()
            //findNavController().popBackStack() // descomentar si se quiere volver de ResumenFragment a SeleccionComidaFragment
        }
    }
}