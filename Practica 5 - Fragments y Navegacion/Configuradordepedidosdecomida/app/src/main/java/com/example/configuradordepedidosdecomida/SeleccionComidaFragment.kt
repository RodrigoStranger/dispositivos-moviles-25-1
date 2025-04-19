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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroupComida = view.findViewById<RadioGroup>(R.id.radioGroupComida)
        val btnSiguiente = view.findViewById<Button>(R.id.btnSiguiente)

        // Escuchar el resultado del fragmento de edición
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val comidaSeleccionada = bundle.getString("comidaSeleccionada")
            if (comidaSeleccionada != null) {
                when (comidaSeleccionada) {
                    getString(R.string.pizza) -> radioGroupComida.check(R.id.radioPizza)
                    getString(R.string.hamburguesa) -> radioGroupComida.check(R.id.radioHamburguesa)
                    getString(R.string.tacos) -> radioGroupComida.check(R.id.radioTacos)
                }
            }
        }

        btnSiguiente.setOnClickListener {
            val comidaSeleccionada = when (radioGroupComida.checkedRadioButtonId) {
                R.id.radioPizza -> getString(R.string.pizza)
                R.id.radioHamburguesa -> getString(R.string.hamburguesa)
                R.id.radioTacos -> getString(R.string.tacos)
                else -> null
            }

            if (comidaSeleccionada != null) {
                val bundle = Bundle().apply {
                    putString("comidaSeleccionada", comidaSeleccionada)
                }
                findNavController().navigate(R.id.action_seleccionComidaFragment_to_seleccionExtrasFragment2, bundle)
            } else {
                Toast.makeText(context, R.string.Error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}