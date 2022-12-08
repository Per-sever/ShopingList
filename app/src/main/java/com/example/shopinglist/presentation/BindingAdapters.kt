package com.example.shopinglist.presentation

import androidx.databinding.BindingAdapter
import com.example.shopinglist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("checkCorrectInputTitle")
fun bindCheckCorrectInputTitle(textInputLayout: TextInputLayout, isCorrect: Boolean) {
    val message = if (isCorrect) {
        textInputLayout.context.getString(R.string.error_title_msg)
    } else {
        null
    }
    textInputLayout.error = message
}


@BindingAdapter("checkCorrectInputQuantity")
fun bindCheckCorrectInputQuantity(textInputLayout: TextInputLayout, isCorrect: Boolean) {
    val message = if (isCorrect) {
        textInputLayout.context.getString(R.string.error_count_msg)
    } else {
        null
    }
    textInputLayout.error = message
}