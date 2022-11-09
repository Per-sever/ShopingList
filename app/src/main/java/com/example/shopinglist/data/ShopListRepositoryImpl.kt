package com.example.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.count.compareTo(o2.count) })

    private var autoIncrementId = 0

    private val shoppingListLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 12) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
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

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shoppingListLiveData
    }

    private fun updateList() {
        shoppingListLiveData.value = shopList.toList()
    }
}