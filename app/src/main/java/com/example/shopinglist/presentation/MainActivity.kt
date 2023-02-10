package com.example.shopinglist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R
import com.example.shopinglist.databinding.ActivityMainBinding
import com.example.shopinglist.presentation.ShopItemActivity.Companion.newIntentAddItem
import com.example.shopinglist.presentation.ShopItemActivity.Companion.newIntentEditItem
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private val component by lazy {
        (application as ShopListApplication).component
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
    private lateinit var adapterShopList: ShopListAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapterShopList.submitList(it)
        }
        val buttonAddItem = binding.addShopItemButton
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
        val rvShopList = binding.rvShopList
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
        return binding.shopItemContainer == null
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

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun onShopItemLongClick() {
        adapterShopList.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

}