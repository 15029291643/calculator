package com.example.calculator.viewModel

import android.provider.SyncStateContract.Helpers.update
import android.text.method.TextKeyListener.clear
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.model.Calculator
import java.nio.file.Files.delete

class MyViewModel : ViewModel() {
    private val _expression = MutableLiveData("")
    val expression: LiveData<String> = _expression

    private val _result = MutableLiveData("")
    val result: LiveData<String> = _result

    fun addExpression(c: Char) {
        when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '-', '*', '/', '(', ')' -> {
                _expression.value = _expression.value + c
                _result.value = try {
                    Calculator.count(_expression.value!!)
                } catch (e: Exception) {
                    ""
                }
            }
            'c' -> {
                _expression.value = ""
            }
            '《' -> {
                if (_expression.value!!.isNotEmpty()) {
                    _expression.value =
                        _expression.value!!.substring(0, _expression.value!!.length - 1)
                    _result.value = try {
                        Calculator.count(_expression.value!!)
                    } catch (e: Exception) {
                        ""
                    }
                }
            }
            '=' -> {
                if (_result.value!!.isNotEmpty()) {
                    _expression.value = _result.value
                    _result.value = ""
                }
            }
        }
    }
}