package com.isma.absolutions.ui.view.OrdersList

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.isma.absolutions.data.model.OrderModel
import com.isma.absolutions.core.UtilsOrder
import com.isma.absolutions.ui.view.MainActivity
import com.isma.absolutions.ui.view.OrdersList.adapter.OrdersListAdapter
import com.isma.absolutions.ui.viewModel.OrdersViewModel
import com.isma.absolutions.databinding.ActivityOrdersListBinding
import com.isma.absolutions.domain.orders.ClickButtonsFilter
import com.isma.absolutions.domain.orders.ClickInItemOrder
import com.isma.absolutions.toString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class OrdersListActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    //Activity lista de ordenes
    private lateinit var binding: ActivityOrdersListBinding

    //Inject en para un Activity
    @Inject
    lateinit var utilsOrder: UtilsOrder

    @Inject
    lateinit var clickInItemOrder: ClickInItemOrder

    @Inject
    lateinit var clickButtonsFilter: ClickButtonsFilter

    //Para poder actualizar la data del recycler view, notificar
    private lateinit var adapter: OrdersListAdapter

    //Para realizar filtros
    private var listOrdersTemp = mutableListOf<OrderModel>()

    private val orderViewModel: OrdersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Butons
        binding.btnBack.setOnClickListener { goBack() }
        binding.btnAll.setOnClickListener { view -> onClickBtnFilter(view, null) }
        binding.btnDelivery.setOnClickListener { view -> onClickBtnFilter(view, 4) }
        binding.btnPickUp.setOnClickListener { view -> onClickBtnFilter(view, 3) }
        binding.btnToGo.setOnClickListener { view -> onClickBtnFilter(view, 2) }
        binding.btnDineIn.setOnClickListener { view -> onClickBtnFilter(view, 1) }

        //initDate
        orderViewModel.onCreate()

        //searchView
        binding.svOrders.setOnQueryTextListener(this)//Para activar los eventos del search view sobre-escritos al final

        //Text View
        val date = Date().toString("yyyy/MM/dd")
        binding.tvNow.text = date

        //Se inicializa el recycler
        orderViewModel.listOrdersResponse.observe(this, Observer { body ->
            val orders: List<OrderModel> = body ?: emptyList()
            listOrdersTemp.clear()
            listOrdersTemp.addAll(orders)
            adapter.notifyDataSetChanged()
        })
        orderViewModel.isLoading.observe(this, Observer { visibility ->
            binding.progress.isVisible = visibility
        })
        initRecyclerView()
    }

    //Muestra un alert dialog al hacer click en una card del recycler view
    //Y realizo el binding de los edit text
    //Se pasa como callback (lambda) al adapter
    private fun handledClickItemOrder(order: OrderModel) {
        clickInItemOrder(order, this, orderViewModel)
    }


    //inicializa el recycler view
    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val rvOrdersList = binding.rvOrdersList
        adapter = OrdersListAdapter(listOrdersTemp, { orderCurrent: OrderModel ->
            handledClickItemOrder(orderCurrent)
        })
        rvOrdersList.adapter = adapter
        rvOrdersList.layoutManager = manager
    }

    //onClick buttons click
    private fun onClickBtnFilter(view: View, orderTypeId: Int?) {
        clickButtonsFilter(view, orderTypeId, orderViewModel, binding)
    }

    //Atras
    private fun goBack() {
        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(searchText: String?): Boolean {
        orderViewModel.searchByFullText(searchText)
        return true
    }

    override fun onQueryTextChange(searchText: String?): Boolean {
        orderViewModel.searchByFullText(searchText)
        return true
    }
}
