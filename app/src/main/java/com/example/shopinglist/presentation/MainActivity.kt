package com.example.shopinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R
import com.example.shopinglist.presentation.ShopItemActivity.Companion.newIntentAddItem
import com.example.shopinglist.presentation.ShopItemActivity.Companion.newIntentEditItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapterShopList: ShopListAdapter

    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        shopItemContainer = findViewById(R.id.shop_item_container)

        viewModel.shopList.observe(this) {
            adapterShopList.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.add_shop_item_button)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = newIntentAddItem(this)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceAddItem()
                launchSecondPane(fragment)
            }
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapterShopList = ShopListAdapter()
        with(rvShopList) {
            adapter = adapterShopList
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        onShopItemLongClick()
        onShopClick()
        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT or
                    ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapterShopList.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        ItemTouchHelper(callback).attachToRecyclerView(rvShopList)
    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun launchSecondPane(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun onShopClick() {
        adapterShopList.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchSecondPane(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun onShopItemLongClick() {
        adapterShopList.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

}