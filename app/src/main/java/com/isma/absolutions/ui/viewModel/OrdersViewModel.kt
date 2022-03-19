package com.isma.absolutions.ui.viewModel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.domain.orders.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrders: GetOrders,
    private val updatedOrder: UpdateOrder,
    private val filterOrderByType: FilterOrderByType
) : ViewModel() {
    private var listOrders = mutableListOf<OrderModel>()

    val listOrdersResponse = MutableLiveData<List<OrderModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getOrders()
            result?.let { res ->
                listOrdersResponse.postValue(res!!.sortedByDescending { order -> order.orderDateTime })
                isLoading.postValue(false)
                listOrders.clear()
                listOrders.addAll(res)
            } ?: run {
                listOrdersResponse.postValue(emptyList())
                isLoading.postValue(false)
            }
        }
    }

    fun showErr(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    //PUT
    fun updateOrderMethod(
        //Order a editar
        order: OrderModel,
        //Para capturar el text de los editText
        view: View,
        //Cerrar el modal
        closeModal: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = updatedOrder(view, order) { msg: String ->
                showErr(
                    view.context,
                    msg
                )
            }
            isLoading.postValue(false)
            val indexOfOrder =
                listOrders.indexOfFirst { orderItem -> order.orderId == orderItem.orderId }
            //Modifico el elemento a actualizar con el index
            response?.let { res ->
                listOrders[indexOfOrder] = res
                listOrdersResponse.postValue(listOrders.sortedByDescending { order -> order.orderDateTime })
                closeModal()
            }
        }
    }

    fun filterOrders(
        orderTypeId: Int?
    ) {
        val filteredOrders = filterOrderByType(listOrders, orderTypeId)
        listOrdersResponse.postValue(filteredOrders?.sortedByDescending { order -> order.orderDateTime } ?: emptyList())
    }

    fun searchByFullText(searchText: String?) {
        val newList = this.listOrders.filter { order ->
            order.orderId.toString().contains(searchText ?: " ") || order.username.toLowerCase().contains(searchText?.toLowerCase() ?: "")
        }
        listOrdersResponse.postValue(newList.sortedByDescending { order -> order.orderDateTime })
    }
}