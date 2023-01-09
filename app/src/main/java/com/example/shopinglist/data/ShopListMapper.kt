package com.example.shopinglist.data

import com.example.shopinglist.domain.ShopItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem = ShopItem(
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled,
        id =
        shopItemDbModel.id
    )

    fun mapListModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> = list.map {
        mapDbModelToEntity(it)
    }
}