package com.example.shopinglist.presentation

import android.app.Application
import com.example.shopinglist.di.DaggerApplicationComponent

class ShopListApplication : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}