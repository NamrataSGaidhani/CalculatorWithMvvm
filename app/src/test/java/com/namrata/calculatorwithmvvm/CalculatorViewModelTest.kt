package com.namrata.calculatorwithmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.namrata.calculatorwithmvvm.viewModel.CalculatorViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp(){
        viewModel = CalculatorViewModel()
    }

    @Test
    fun onDigit_should_append_digit() {
        viewModel.onDigit("1")
        viewModel.onDigit("2")
        assertEquals("12", viewModel.inputText.value)
    }
    @Test
    fun onDecimalPoint_should_append_dot_if_lastNumber_is_true_and_lastDot_is_false(){
        viewModel.onDigit("1")
        viewModel.onDecimalPoint()
        assertEquals("1.",viewModel.inputText.value)
    }

    @Test
    fun onOperator_should_append_operator_if_last_is_numeric_and_no_operator_exists(){
        viewModel.onDigit("1")
        viewModel.onOperator("+")
        assertEquals("1+", viewModel.inputText.value)
    }

    @Test
    fun onOperator_should_not_append_operator_if_already_added(){
        viewModel.onDigit("3")
        viewModel.onOperator("+")
        viewModel.onOperator("-")
        assertEquals("3+", viewModel.inputText.value)
        assertEquals("Enter a valid operator", viewModel.errorMessage.value)
    }

    @Test
    fun onClear_should_reset_input_text(){
        viewModel.onDigit("5")
        viewModel.onClear()
        assertEquals("",viewModel.inputText.value)
    }

    @Test
    fun onEqual_should_calculate_result(){
        viewModel.onDigit("6")
        viewModel.onOperator("*")
        viewModel.onDigit("3")
        viewModel.onEqual()
        assertEquals("18", viewModel.inputText.value)
    }

    @Test
    fun onBack_should_remove_last_character(){
        viewModel.onDigit("5")
        viewModel.onDigit("6")
        viewModel.onBack()
        assertEquals("5", viewModel.inputText.value)
    }
}


