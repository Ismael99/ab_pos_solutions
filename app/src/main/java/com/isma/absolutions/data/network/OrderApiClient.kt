package com.isma.absolutions.data.network

import com.isma.absolutions.data.model.OrderInfoModel
import com.isma.absolutions.data.model.OrderModel
import retrofit2.Response
import retrofit2.http.*

//Metodos que interactuan directamente con la api por medio de retrofit
interface OrderApiClient {
    @GET
    suspend fun getOrders(@Url url: String): Response<List<OrderModel>>

    @PUT
    suspend fun updateOrder(@Url url: String, @Body order: OrderModel): Response<OrderModel>
}