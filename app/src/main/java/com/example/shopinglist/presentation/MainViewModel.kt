package com.example.shopinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopinglist.domain.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListItemUseCase: GetShopListUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase
) : ViewModel() {


    val shopList = getShopListItemUseCase.getShopList()


    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

}