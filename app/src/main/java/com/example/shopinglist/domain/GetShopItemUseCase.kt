package com.example.shopinglist.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItem(idShopItem: Int): ShopItem {
        return shopListRepository.getShopItem(idShopItem)
    }
}