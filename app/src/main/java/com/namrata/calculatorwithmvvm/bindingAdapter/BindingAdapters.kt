package com.namrata.calculatorwithmvvm.bindingAdapter

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.namrata.calculatorwithmvvm.viewModel.CalculatorViewModel

object  BindingAdapters {
    @JvmStatic
    @BindingAdapter("onBackClick")
    fun backButtonClick(button: ImageButton,viewModel: CalculatorViewModel){
        button.setOnClickListener{
            viewModel.onBack()
        }
    }
}