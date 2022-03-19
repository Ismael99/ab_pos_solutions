package com.isma.absolutions.domain.orders

import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.isma.absolutions.R
import com.isma.absolutions.core.UtilsOrder
import com.isma.absolutions.data.OrderRepository
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.toString
import java.util.*
import javax.inject.Inject

class UpdateOrder @Inject constructor(
    private val repository: OrderRepository,
    private val utilsOrders: UtilsOrder
) {
    suspend operator fun invoke(
        view: View,
        order: OrderModel,
        showError: (msg: String) -> Unit
    ): OrderModel? {
        val etUsername = view.findViewById<EditText>(R.id.etUsername)
        val etSubTotal = view.findViewById<EditText>(R.id.etSubTotal)
        val orderType = view.findViewById<Spinner>(R.id.etOrderType)
        //Obtengo el id del orderId
        val orderTypeId = utilsOrders.findOrderType(orderType.selectedItem.toString())
        //Se crea un nuevo objeto del tipo OrderModel, con los campos actualizados
        if (etUsername.text.isNotEmpty() && etSubTotal.text.isNotEmpty() && (etUsername.text.toString() != order.username || etSubTotal.text.toString().toDouble() != order.subTotal || orderTypeId != order.orderType)){
            //Fecha y hora actual, en el formato requerido
            val dateNow = Date().toString("dd/MM/yyyy hh:mm:ss a")

            val newOrder = OrderModel(
                order.orderId,
                etUsername.text.toString(),
                etSubTotal.text.toString().toDouble(),
                order.ticketNumber,
                dateNow,
                orderTypeId
            )
            return repository.updateOrder(newOrder)
        } else {
            showError("Campos obligatorios vacios o no se han realizado campbios!!!")
            return null
        }

    }
}