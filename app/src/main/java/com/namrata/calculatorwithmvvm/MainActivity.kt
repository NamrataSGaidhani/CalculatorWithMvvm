package com.namrata.calculatorwithmvvm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private var btnOne: Button?=null
    private var btnTwo: Button?=null
    private var btnThree: Button?=null
    private var btnFour: Button?=null
    private var btnFive: Button?=null
    private var btnSix: Button?=null
    private var btnSeven: Button?=null
    private var btnEight: Button?=null
    private var btnNine: Button?=null
    private var btnZero: Button?=null
    private var tvInput: TextView?=null
    private var btnDecimal: Button?=null
    private var btnAdd: Button?=null
    private var btnSubtract: Button?=null
    private var btnMultiply: Button?=null
    private var btnDivide: Button?=null
    private var btnEqual: Button?=null
    private var btnClear: Button?=null
    var lastNumeric = false
    var lastDot =false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        tvInput =findViewById(R.id.tvInput)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive= findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven  = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnZero = findViewById(R.id.btnZero)
        btnDecimal = findViewById(R.id.btnDecimal)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEqual = findViewById(R.id.btnEqual)
        btnClear = findViewById(R.id.btnClear)

        btnOne?.setOnClickListener(this)
        btnTwo?.setOnClickListener(this)
        btnThree?.setOnClickListener(this)
        btnFour?.setOnClickListener(this)
        btnFive?.setOnClickListener(this)
        btnSix?.setOnClickListener(this)
        btnSeven?.setOnClickListener(this)
        btnEight?.setOnClickListener(this)
        btnNine?.setOnClickListener(this)
        btnZero?.setOnClickListener(this)
        btnDecimal?.setOnClickListener(this)
        btnAdd?.setOnClickListener(this)
        btnSubtract?.setOnClickListener(this)
        btnMultiply?.setOnClickListener(this)
        btnDivide?.setOnClickListener(this)
        btnEqual?.setOnClickListener(this)
        btnClear?.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnOne -> onDigit(view)
            R.id.btnTwo -> onDigit(view)
            R.id.btnThree -> onDigit(view)
            R.id.btnFour -> onDigit(view)
            R.id.btnFive -> onDigit(view)
            R.id.btnSix -> onDigit(view)
            R.id.btnSeven -> onDigit(view)
            R.id.btnEight -> onDigit(view)
            R.id.btnNine -> onDigit(view)
            R.id.btnZero -> onDigit(view)
            R.id.btnDecimal -> onDecimalPoint()
            R.id.btnClear -> onClear()
            R.id.btnAdd -> onOperator(view)
            R.id.btnSubtract -> onOperator(view)
            R.id.btnMultiply -> onOperator(view)
            R.id.btnDivide -> onOperator(view)
            R.id.btnEqual -> onEqual()




        }    }

    private fun onEqual() {
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix =""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeZeroAfterDot(((one.toDouble() - two.toDouble())).toString())

                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeZeroAfterDot(((one.toDouble() * two.toDouble())).toString())
                }
                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeZeroAfterDot(((one.toDouble() + two.toDouble())).toString())
                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    tvInput?.text = removeZeroAfterDot(((one.toDouble() / two.toDouble())).toString())
                }

            }catch (e :ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun onOperator(view: View) {

        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }else{
                Toast.makeText(this,"Enter a valid operator", Toast.LENGTH_SHORT).show()

            }
        }

    }


    private fun onClear() {
        tvInput?.text=""
        lastNumeric = false
        lastDot = false
    }

    private fun onDecimalPoint() {
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }


    private fun isOperatorAdded(value :String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+")||value.contains("-")||value.contains("*")||value.contains("/")
        }
    }
    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

}