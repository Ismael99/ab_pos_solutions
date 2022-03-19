package com.isma.absolutions.core

import com.isma.absolutions.data.model.DataOrderProvider

object UtilsOrder {
    fun findOrderType(orderTypeName: String):Int{
        return  DataOrderProvider.types.find { type -> type.typeName == orderTypeName }?.orderTypeId ?: 0
    }
    fun getArrayOrderTypesNames():List<String>{
        return DataOrderProvider.types.map { type -> type.typeName }
    }
    fun getTypeOrderById(typeId:Int):String?{
        val typeNameCurrent = DataOrderProvider.types.find { type -> type.orderTypeId== typeId}?.typeName
        return typeNameCurrent
    }
}