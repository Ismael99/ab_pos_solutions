package com.isma.absolutions.data.model

class DataOrderProvider {
    companion object {
        val types: ArrayList<OrderTypeModel> = arrayListOf(
            OrderTypeModel(1, "Dine In"),
            OrderTypeModel(2, "To Go"),
            OrderTypeModel(3, "Pick Up"),
            OrderTypeModel(4, "Delivery"),
        )
    }
}