package com.example.shopinglist.di

import android.app.Application
import com.example.shopinglist.data.AppDatabase
import com.example.shopinglist.data.ShopListDao
import com.example.shopinglist.data.ShopListRepositoryImpl
import com.example.shopinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {
    @ApplicationScope
    @Binds
    abstract fun bindShopListRepositoryImpl(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).ShopListDao()
        }
    }
}