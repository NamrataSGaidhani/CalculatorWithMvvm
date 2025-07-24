package com.namrata.calculatorwithmvvm

import com.namrata.calculatorwithmvvm.model.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CalculatorParameterizedTest {

    @ParameterizedTest
    @CsvSource(
        "1+2,3",
        "10-3,7",
        "5*2,10",
        "8/2,4",
        "6/4,1.5",

    )
    fun calculate_should_return_expected_result(expression: String, expected: String) {
        val result = Calculator.calculate(expression)
        assertEquals(expected, result)
    }
}