package com.example.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao: ShopListDao,
    private val mapper: ShopListMapper
) : ShopListRepository {


    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(idShopItem: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(idShopItem)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return Transformations.map(shopListDao.getShopList()) { mapper.mapListModelToListEntity(it) }
    }

}