package com.isma.absolutions.data.network

import com.isma.absolutions.core.RetrofitHelper
import com.isma.absolutions.data.model.OrderInfoModel
import com.isma.absolutions.data.model.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//Se colocaran todos los metodos que permita utilizar el cliente de la api y retornaran el response.body()
// por medio de retrofit
class OrderService @Inject constructor(private val api: OrderApiClient) {

    suspend fun getOrders(): List<OrderModel> {
        //Ejecutralo en un hilo secundario
        return withContext(Dispatchers.IO) {
            val response = api.getOrders("getOrders")
            response.body() ?: emptyList()
        }
    }

    suspend fun updateOrder(updatedOrder: OrderModel): OrderModel? {
        return withContext(Dispatchers.IO) {
            val response = api.updateOrder("updateOrder", updatedOrder)
            println(response)
            response.body()
        }
    }
}