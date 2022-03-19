package com.isma.absolutions.data

import com.isma.absolutions.data.model.OrderInfoModel
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.data.network.OrderService
import javax.inject.Inject

//Se encarga de determinar de donde extraer la informacion, en este caso de la API
//Utilizando el servicio de Ordenes
class OrderRepository @Inject constructor(
    private val service: OrderService
) {
    suspend fun getAllOrders(): List<OrderModel> {
        val response = service.getOrders()
        return response
    }

    suspend fun updateOrder(updatedOrder: OrderModel): OrderModel? {
        val response = service.updateOrder(updatedOrder)
        return response
    }
}