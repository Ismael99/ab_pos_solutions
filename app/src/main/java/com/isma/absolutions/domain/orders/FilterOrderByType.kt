package com.isma.absolutions.domain.orders

import com.isma.absolutions.data.model.OrderModel
import javax.inject.Inject

class FilterOrderByType @Inject constructor() {
    operator fun invoke(orders: List<OrderModel>, orderTypeId:Int?): List<OrderModel>?{
        if (orderTypeId == null) {
            return orders
        } else {
            return orders.filter { order -> order.orderType == orderTypeId }
        }
    }
}