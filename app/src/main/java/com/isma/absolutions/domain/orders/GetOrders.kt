package com.isma.absolutions.domain.orders

import com.isma.absolutions.data.OrderRepository
import com.isma.absolutions.data.model.OrderModel
import javax.inject.Inject

//Se van a definir los casos de uso, la logica de negocio

class GetOrders @Inject constructor(
    private val repository: OrderRepository
) {
    suspend operator fun invoke():List<OrderModel>?{
        return repository.getAllOrders()
    }
}