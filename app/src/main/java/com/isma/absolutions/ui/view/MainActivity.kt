package com.isma.absolutions.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.isma.absolutions.R
import com.isma.absolutions.ui.view.OrdersList.OrdersListActivity
import com.isma.absolutions.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ABSolutions)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Asigancion de evento onClick a botones
        binding.btn0?.setOnClickListener { view -> onClick(view) }
        binding.btn1?.setOnClickListener { view -> onClick(view) }
        binding.btn2?.setOnClickListener { view -> onClick(view) }
        binding.btn3?.setOnClickListener { view -> onClick(view) }
        binding.btn4?.setOnClickListener { view -> onClick(view) }
        binding.btn5?.setOnClickListener { view -> onClick(view) }
        binding.btn6?.setOnClickListener { view -> onClick(view) }
        binding.btn7?.setOnClickListener { view -> onClick(view) }
        binding.btn8?.setOnClickListener { view -> onClick(view) }
        binding.btn9?.setOnClickListener { view -> onClick(view) }
        binding.btnGo?.setOnClickListener { view -> onClick(view) }
        binding.btnClear?.setOnClickListener { view -> onClick(view) }

        binding.showHiddenPassword?.setOnClickListener { view -> hiddenShow(view) }

        //init value tag
        binding.showHiddenPassword?.setTag("hidden")
    }

    private fun onClick(view: View) {
        val text: String? = (view as Button).text as String?
        val etPassword = binding.etPassword
        val lengthEtPassword = etPassword.text.length
        println(text)
        if (text == "Clear" && lengthEtPassword > 0) {
            val newText: String? = binding.etPassword.text.substring(0, lengthEtPassword - 1)
            binding.etPassword.setText(newText)
        } else if (text == "Go") {
            val etPassword: EditText = binding.etPassword
            if (etPassword.text.length < 5) {
                val Toast =
                    Toast.makeText(this, "Caracteres minimos para password 5", Toast.LENGTH_SHORT)
                Toast.show()
            } else {
                val intent: Intent = Intent(this, OrdersListActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.etPassword.text.append("${(view as Button).text}")
        }
    }

    private fun hiddenShow(view: View) {
        val icon: ImageView? = binding.showHiddenPassword
        val etPassword = binding.etPassword
        val etPasswordLength:Int = etPassword.text.length
        val tag = (view as ImageView).tag
        if (tag == "hidden") {
            icon?.setTag("show")
            icon?.setImageResource(R.drawable.ic_eye_slash_regular)
            etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //Volver el cursor a la ultima posicion
            etPassword.setSelection(etPasswordLength)

        } else {
            icon?.setTag("hidden")
            icon?.setImageResource(R.drawable.ic_eye_regular)
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            //Volver el cursor a la ultima posicion
            etPassword.setSelection(etPasswordLength)
        }
    }

}