package com.example.shopinglist.data

import com.example.shopinglist.data.ShopListRepositoryImpl.shopList
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(idShopItem: Int): ShopItem {
        return shopList.find { it.id == idShopItem } ?: throw RuntimeException(
            "Element with id  " +
                    "$idShopItem not found"
        )
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}