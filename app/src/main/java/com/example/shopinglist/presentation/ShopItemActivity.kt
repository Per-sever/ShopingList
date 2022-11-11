package com.example.shopinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglist.R
import com.example.shopinglist.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var titleName: TextInputLayout
//    private lateinit var titleCount: TextInputLayout
//    private lateinit var saveButton: Button
//
//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        parseIntent()
//        observeViewModel()
//        addTextChangeListeners()
//        launchMode()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_title_msg)
//            } else {
//                null
//            }
//            titleCount.error = message
//        }
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_count_msg)
//            } else {
//                null
//            }
//            titleName.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchMode() {
//        when (screenMode) {
//            MODE_ADD -> launchAddMode()
//            MODE_EDIT -> launchEditMode()
//        }
//    }
//
//    private fun launchAddMode() {
//        saveButton.setOnClickListener {
//            viewModel.addShopItemUseCase(etName.text?.toString(), etCount.text?.toString())
//        }
//
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItemUseCase(shopItemId)
//        viewModel.shopItemById.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        saveButton.setOnClickListener {
//            viewModel.editShopItemUseCase(etName.text?.toString(), etCount.text?.toString())
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen is absent")
//        }
//
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknown screen mode - $mode")
//        }
//        screenMode = mode
//
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException("Param shop item id is absent")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputCount()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//
//    private fun initViews() {
//        etName = findViewById(R.id.etName)
//        etCount = findViewById(R.id.etCount)
//        titleName = findViewById(R.id.title_name)
//        titleCount = findViewById(R.id.title_count)
//        saveButton = findViewById(R.id.button_save)
//    }
//
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, idShopItem: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, idShopItem)
            return intent
        }
    }
}