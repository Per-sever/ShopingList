package com.example.shopinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shopinglist.data.ShopListRepositoryImpl
import com.example.shopinglist.domain.DeleteShopItemUseCase
import com.example.shopinglist.domain.EditShopItemUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListItemUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)


    val shopList = getShopListItemUseCase.getShopList()

//    val shopList = MutableLiveData<List<ShopItem>>()


    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)

    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}