package com.namrata.calculatorwithmvvm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.namrata.calculatorwithmvvm.viewModel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private lateinit var ib_back: ImageButton
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val buttonId= listOf(
            R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven,
            R.id.btnEight, R.id.btnNine, R.id.btnDecimal, R.id.btnAdd,
            R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide, R.id.btnClear,R.id.btnBack,
            R.id.btnEqual
        )

        tvInput = findViewById(R.id.tvInput)
        ib_back = findViewById(R.id.ib_back)

        buttonId.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
        ib_back.setOnClickListener {
            viewModel.onBack()
        }

        viewModel.inputText.observe(this) {
            tvInput.text = it
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onButtonClick(view: View?) {
        when(view?.id){

            R.id.btnZero -> viewModel.onDigit("0")
            R.id.btnOne -> viewModel.onDigit("1")
            R.id.btnTwo -> viewModel.onDigit("2")
            R.id.btnThree -> viewModel.onDigit("3")
            R.id.btnFour -> viewModel.onDigit("4")
            R.id.btnFive -> viewModel.onDigit("5")
            R.id.btnSix -> viewModel.onDigit("6")
            R.id.btnSeven -> viewModel.onDigit("7")
            R.id.btnEight -> viewModel.onDigit("8")
            R.id.btnNine -> viewModel.onDigit("9")
            R.id.btnDecimal -> viewModel.onDecimalPoint()
            R.id.btnAdd -> viewModel.onOperator("+")
            R.id.btnSubtract -> viewModel.onOperator("-")
            R.id.btnMultiply -> viewModel.onOperator("*")
            R.id.btnDivide -> viewModel.onOperator("/")
            R.id.btnEqual -> viewModel.onEqual()
            R.id.btnClear -> viewModel.onClear()
            //R.id.btnBack -> viewModel.onBack()

            else -> {
                // Handle unknown button clicks if necessary
            }
        }

    }
}