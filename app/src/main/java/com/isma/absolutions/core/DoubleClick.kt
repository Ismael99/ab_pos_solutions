package com.isma.absolutions.core

import android.view.View

abstract class DoubleClick : View.OnClickListener {
    var lastClickTime: Long = 0
    override fun onClick(view: View?) {
        val clickTime = System.currentTimeMillis()//Hora actual en milisegundos
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {//El tiempo en ms para considerarlo como doubleClick
            onDoubleClick(view)
        }
        lastClickTime = clickTime
    }

    abstract fun onDoubleClick(view: View?)


    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 500 //milliseconds
    }
}