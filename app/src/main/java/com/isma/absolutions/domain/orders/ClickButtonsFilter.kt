package com.isma.absolutions.domain.orders

import android.content.res.Resources
import android.view.View
import android.widget.Button
import android.widget.ResourceCursorAdapter
import com.isma.absolutions.R
import com.isma.absolutions.databinding.ActivityOrdersListBinding
import com.isma.absolutions.ui.viewModel.OrdersViewModel
import javax.inject.Inject

class ClickButtonsFilter @Inject constructor() {
    operator fun invoke (view:View, orderTypeId:Int?, orderViewModel:OrdersViewModel,binding: ActivityOrdersListBinding){
        val currentId = view.id
        val btnAll = binding.btnAll
        val btnToGo = binding.btnToGo
        val btnDineIn = binding.btnDineIn
        val btnPickUp = binding.btnPickUp
        val btnDelivey = binding.btnDelivery
        //Array de botones
        val arrayButtons: List<Button> =
            mutableListOf(btnAll, btnDelivey, btnDineIn, btnDineIn, btnPickUp, btnToGo)
        //Cambiar el color a los botones e los filtros
        arrayButtons.forEach { button ->
            if (button.id == currentId) {
                button.setBackgroundColor(button.context.resources.getColor(R.color.acent_color2))
            } else {
                when(button.text){
                    "All" -> button.setBackgroundColor(button.context.resources.getColor(R.color.primary))
                    "To Go" -> button.setBackgroundColor(button.context.resources.getColor(R.color.secundary))
                    "Dine In" -> button.setBackgroundColor(button.context.resources.getColor(R.color.hint_color))
                    "Pick Up" -> button.setBackgroundColor(button.context.resources.getColor(R.color.acent_color))
                    "Delivery" -> button.setBackgroundColor(button.context.resources.getColor(R.color.primary_ligth))
                }
            }
        }
        //Metodo de OrderViewModel que actualiza la lista de orders a mostrar en el recycle view
        //en base a el orderId
        orderViewModel.filterOrders(orderTypeId)
        //acceder al valor del id o tomar el mismo orderId para asignarle stilo al boton activo
    }
}