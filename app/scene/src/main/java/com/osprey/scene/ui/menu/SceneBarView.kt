package com.osprey.scene.ui.menu

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osprey.core.extension.AbstractView
import com.osprey.scene.BR
import com.osprey.scene.R
import com.osprey.scene.databinding.ItemTopBarViewBinding

class SceneBarView(context: Context, attrs: AttributeSet?) : AbstractView(context, attrs) {
    interface OnTopBarViewListener {
        fun onClick(menu: SceneBarMenu)
    }

    var listener: OnTopBarViewListener? = null

    var data: List<SceneBarMenu> = arrayListOf()
        set(value) {
            field = value
            if (value.isEmpty()) {
                return
            }
            viewBinding().recyclerView.layoutManager = object :
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.width = viewBinding().recyclerView.width / value.size
                    return true
                }
            }

            data.firstOrNull()?.isSelected = true
            binding.setVariable(BR.menus, data)
            binding.executePendingBindings()
        }

    override fun layoutId(): Int = R.layout.item_top_bar_view

    override fun viewBinding() = binding as ItemTopBarViewBinding

    override fun viewInitialized() {
        setupRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun selectPosition(position: Int) {
        for ((index, item) in data.withIndex()) {
            item.isSelected = position == index
        }
        viewBinding().recyclerView.adapter?.notifyDataSetChanged()
        data.firstOrNull { it.isSelected }?.let { listener?.onClick(it) }
    }

    private fun setupRecyclerView() {
        with(viewBinding()) {
            recyclerView.adapter =
                SceneBarAdapter(context, object : SceneBarAdapter.OnItemClickListener {
                    override fun onClick(menu: SceneBarMenu) {
                        listener?.onClick(menu)
                    }
                })
        }
    }
}

object TaskBarBindingAdapter {
    @BindingAdapter(value = ["items"])
    @JvmStatic
    fun <T> setItems(view: SceneBarView, items: List<SceneBarMenu>?) {
        view.data = items ?: arrayListOf()
    }
}