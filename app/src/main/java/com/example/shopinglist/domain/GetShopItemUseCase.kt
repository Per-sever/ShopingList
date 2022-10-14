package com.example.shopinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(idShopItem: Int): ShopItem {
        return shopListRepository.getShopItem(idShopItem)
    }
}