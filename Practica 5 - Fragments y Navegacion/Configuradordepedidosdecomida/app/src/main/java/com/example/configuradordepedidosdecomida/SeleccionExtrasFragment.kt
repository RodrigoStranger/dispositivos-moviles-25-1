package com.example.configuradordepedidosdecomida

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SeleccionExtrasFragment : Fragment(R.layout.fragment_seleccion_extras) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroupExtras = view.findViewById<RadioGroup>(R.id.radioGroupExtras)
        val btnSiguiente = view.findViewById<Button>(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {
            val extrasSeleccionados = when (radioGroupExtras.checkedRadioButtonId) {
                R.id.radioGaseosa -> getString(R.string.gaseosa)
                R.id.radioEnsalada -> getString(R.string.ensalada)
                R.id.radioPostre -> getString(R.string.postre)
                R.id.radioPapasFritas -> getString(R.string.papas_fritas)
                R.id.radioNingunExtra -> getString(R.string.ningun_extra)
                else -> null
            }

            if (extrasSeleccionados != null) {
                val bundle = Bundle().apply {
                    putString("comidaSeleccionada", arguments?.getString("comidaSeleccionada"))
                    putString("extrasSeleccionados", extrasSeleccionados)
                }
                findNavController().navigate(R.id.action_seleccionExtrasFragment_to_resumenPedidoFragment2, bundle)
            } else {
                Toast.makeText(context, R.string.Error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}