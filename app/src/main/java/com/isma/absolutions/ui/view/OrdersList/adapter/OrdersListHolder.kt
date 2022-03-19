package com.isma.absolutions.ui.view.OrdersList.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.isma.absolutions.data.model.DataOrderProvider
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.data.model.OrderTypeModel
import com.isma.absolutions.R
import com.isma.absolutions.databinding.ItemOrderBinding

class OrdersListHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(order: OrderModel, onClick: (OrderModel) -> Unit){
        val root = binding.root
        binding.headerOrder.text = "Order#: ${order.orderId} User: ${order.username} Total: $${order.subTotal} Ticket#: ${order.ticketNumber}"
        binding.tvDate.text = "Order Time: ${order.orderDateTime.toString().toUpperCase()}"

        //Set onclickListener
        root.setOnClickListener{ onClick(order)}

        //Current type order
        val orderTypeCurrent:OrderTypeModel? = DataOrderProvider.types.find { type ->
            type.orderTypeId == order.orderType
        }
        binding.tvTypeOrder.text = orderTypeCurrent?.typeName

        if(order.orderType==1){
            root.setBackgroundResource(R.color.hint_color)
        }else if(order.orderType==2){
            root.setBackgroundResource(R.color.secundary)
        }else if(order.orderType==3){
            root.setBackgroundResource(R.color.acent_color)
        }else if(order.orderType==4){
            root.setBackgroundResource(R.color.primary_ligth)
        }
    }
}