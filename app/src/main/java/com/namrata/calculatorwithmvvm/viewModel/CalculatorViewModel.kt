package com.namrata.calculatorwithmvvm.viewModel

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namrata.calculatorwithmvvm.model.Calculator

class CalculatorViewModel:ViewModel(){

    private val _inputText = MutableLiveData<String>("")
    private val _errorMessage = MutableLiveData<String>()

    val inputText: LiveData<String> = _inputText
    val errorMessage: LiveData<String> = _errorMessage

    var lastNumeric = false
    var lastDot =false

     fun onDigit(digit: String) {
        _inputText.value =(_inputText.value?:"") + digit
        lastNumeric = true
        lastDot = false
    }

     fun onDecimalPoint() {
        if (lastNumeric && !lastDot){
            _inputText.value =(_inputText.value?:"") + "."
            lastNumeric = false
            lastDot = true
        }
    }

         fun onOperator(operator: String) {
            val value = _inputText.value ?: ""
            if (lastNumeric && !isOperatorAdded(value)) {
                _inputText.value = value + operator
                lastNumeric = false
                lastDot = false
            } else {
                _errorMessage.value = "Enter a valid operator"
            }
    }


     fun isOperatorAdded(value :String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+")||value.contains("-")||value.contains("*")||value.contains("/")
        }
    }

     fun onClear() {
        _inputText.value = ""
        lastNumeric = false
        lastDot = false
    }

    fun onEqual() {
        if (lastNumeric) {
            _inputText.value = Calculator.calculate(_inputText.value ?: "")
        }
    }


    fun onBack() {
        val currentInput = _inputText.value ?: return
        if (currentInput.isNotEmpty()) {
            val updatedInput = currentInput.dropLast(1)
            _inputText.value = updatedInput
        }
    }

}