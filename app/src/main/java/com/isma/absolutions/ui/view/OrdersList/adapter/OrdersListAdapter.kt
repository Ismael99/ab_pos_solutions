package com.isma.absolutions.ui.view.OrdersList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.databinding.ItemOrderBinding

class OrdersListAdapter(
    private val orders: List<OrderModel>,
    private val onClick: (OrderModel) -> Unit
) :
    RecyclerView.Adapter<OrdersListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListHolder {
        val bindingItemOrder: ItemOrderBinding =
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersListHolder(bindingItemOrder)
    }

    override fun onBindViewHolder(holder: OrdersListHolder, position: Int) {
        val currentOrder: OrderModel = orders[position]
        holder.render(currentOrder, onClick)
    }

    override fun getItemCount(): Int = orders.size

}
