package com.isma.absolutions.data.model

data class OrderModel(
    val orderId: Int,
    var username: String,
    val subTotal: Double,
    val ticketNumber: Int,
    val orderDateTime: String,
    val orderType: Int
)
