package com.example.configuradordepedidosdecomida

/*
    Autor: Rodrigo Emerson Infanzon Acosta
    Curso: Programacion De Dispositivos Moviles
    Semestre: VI
    Fecha: 19/03/2025
    Ultima modificacion: 23/03/2025 11:16 am
*/

/*
    Configurador de pedido de comida.
    Objetivo: Permitir al usuario armar un pedido paso a paso y visualizarlo al finalizar.

    Fragments:
    InicioFragment
        - Botón: "Nuevo pedido" → navega a SeleccionComidaFragment.

    SeleccionComidaFragment
        - Selección de comida (ej: pizza, hamburguesa, ensalada).
        - Al hacer clic en “Siguiente”: Se crea un Bundle con el tipo de comida seleccionado.
        - Se navega a SeleccionExtrasFragment.

    SeleccionExtrasFragment
        - Selección de extras (bebida, papas, postre).
        - Al hacer clic en “Siguiente”: Se agrega la info de los extras al mismo Bundle o se
          crea uno nuevo con todos los datos.
        - Se navega a ResumenPedidoFragment.

    ResumenPedidoFragment
    - Usa arguments?.getString(...), etc. para mostrar la comida y extras.
    - Botones:
        - “Confirmar pedido”: muestra un Toast y vuelve al inicio (navigate()).
        - "Editar pedido”:
        - Usa setFragmentResult() con los datos actuales del pedido.
        - Llama a popBackStack() para regresar a SeleccionComidaFragment,
          que los puede recuperar con setFragmentResultListener(...).
*/

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }
}