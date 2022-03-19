package com.isma.absolutions.domain.orders

import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.isma.absolutions.R
import com.isma.absolutions.core.UtilsOrder
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.ui.viewModel.OrdersViewModel
import javax.inject.Inject


class ClickInItemOrder @Inject constructor(
    private val utilsOrders: UtilsOrder
) {

    operator fun invoke(
        order: OrderModel,
        context: Context,
        orderViewModel: OrdersViewModel,
    ) {
        //Create builder of modal
        val builder = AlertDialog.Builder(context)
        //generate view to show in modal
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.component_view_edit_order, null)

        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnCloseModal = view.findViewById<ImageButton>(R.id.btnCloseModal)


        if (order != null) {
            //Binding
            val etUsername = view.findViewById<EditText>(R.id.etUsername)
            val etSubTotal = view.findViewById<EditText>(R.id.etSubTotal)
            val tvOrderNumber = view.findViewById<TextView>(R.id.tvOrderNumber)
            val tvTicketNumber = view.findViewById<TextView>(R.id.tvTickectNumber)
            val tvOrderDateTime = view.findViewById<TextView>(R.id.tvDateTime)
            val typeOrder = view.findViewById<Spinner>(R.id.etOrderType)

            //settear valores en los textView
            ("Order# ${order.orderId}").also { text -> tvOrderNumber.text = text }
            ("Ticket# ${order.ticketNumber}").also { text -> tvTicketNumber.text = text }
            order.orderDateTime.also { text -> tvOrderDateTime.text = text.toUpperCase() }

            //Para el spinner, dropdown
            val types = utilsOrders.getArrayOrderTypesNames()
            val adapterAutoComplete =
                ArrayAdapter(
                    view.context,
                    android.R.layout.simple_spinner_item,
                    types
                )//Creo el adapter del spinner con el array de datos a mostrar
            adapterAutoComplete.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            typeOrder.setAdapter(adapterAutoComplete)
            //Setear valores en los edit text
            etUsername.setText(order.username)
            etSubTotal.setText(order.subTotal.toString())
            //Iniciar el spinner con el valor por defecto
            val typeCurrent =
                utilsOrders.getTypeOrderById(order.orderType)//Obtengo el string a partir del id
            val positionDefault =
                adapterAutoComplete.getPosition(typeCurrent)//Obtengo la pocision del options
            typeOrder.setSelection(
                positionDefault,
                true
            )//Le asigno dicha posicion como valor por defecto

        }
        //setear la view a mostrar en el alertDialog
        builder.setView(view)
        //Mostrar modal
        val alert = builder.show()

        //Para mostrar los bordes redondeados
        alert.window?.setBackgroundDrawableResource(R.color.bg_transparent)
        //Add setOnclickListener
        btnCloseModal.setOnClickListener { alert.dismiss() }
        btnSave.setOnClickListener {
            orderViewModel.updateOrderMethod(
                order,
                view,
                { alert.dismiss() })
        }
    }

}