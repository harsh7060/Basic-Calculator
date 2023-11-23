package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNum: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById<TextView>(R.id.tv_input)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNum = true
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNum && !lastDot) {
            tvInput?.append(".")
            lastDot = true
            lastNum = false
        }
    }

    fun  onEqual(view: View){
        var tvValue = tvInput?.text.toString()
        var prefix = ""
        try {
            if(tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            if(tvValue.contains("-")){
                var splitValue = tvValue.split("-")
                var one = splitValue[0]
                var two = splitValue[1]
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }
                tvInput?.text = (one.toDouble() - two.toDouble()).toString()
            }
            else if(tvValue.contains("+")){
                var splitValue = tvValue.split("+")
                var one = splitValue[0]
                var two = splitValue[1]
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }
                tvInput?.text = (one.toDouble() + two.toDouble()).toString()
            }
            else if(tvValue.contains("*")){
                var splitValue = tvValue.split("*")
                var one = splitValue[0]
                var two = splitValue[1]
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }
                tvInput?.text = (one.toDouble() * two.toDouble()).toString()
            }
            else if(tvValue.contains("/")){
                var splitValue = tvValue.split("/")
                var one = splitValue[0]
                var two = splitValue[1]
                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }
                tvInput?.text = (one.toDouble() / two.toDouble()).toString()
            }
        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNum && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNum = false
                lastDot = false
            }
        }
    }


    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }
}

