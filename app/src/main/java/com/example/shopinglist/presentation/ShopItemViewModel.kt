package com.example.shopinglist.presentation

import androidx.lifecycle.*
import com.example.shopinglist.domain.AddShopItemUseCase
import com.example.shopinglist.domain.EditShopItemUseCase
import com.example.shopinglist.domain.GetShopItemUseCase
import com.example.shopinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopItemViewModel @Inject constructor(
    private val addShopItemUseCase: AddShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val getShopItemUseCase: GetShopItemUseCase
) : ViewModel() {


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItemById = MutableLiveData<ShopItem>()
    val shopItemById: LiveData<ShopItem>
        get() = _shopItemById

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun addShopItemUseCase(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }

        }
    }


    fun editShopItemUseCase(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            viewModelScope.launch {
                _shopItemById.value?.let {
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(item)
                    finishWork()
                }
            }


        }
    }

    fun getShopItemUseCase(shopItemId: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemId)
            _shopItemById.value = item
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}