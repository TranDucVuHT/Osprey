package com.osprey.scene.ui.menu



import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.osprey.core.viewmodel.ui.TMVVMAdapter
import com.osprey.core.viewmodel.ui.TMVVMViewHolder
import com.osprey.scene.BR
import com.osprey.scene.databinding.ItemSceneBarLayoutBinding

class SceneBarAdapter(private val context: Context, private val listener: OnItemClickListener) :
    TMVVMAdapter<SceneBarMenu>(ArrayList()) {
    interface OnItemClickListener {
        fun onClick(menu: SceneBarMenu)
    }

    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): TMVVMViewHolder {
        val binding = ItemSceneBarLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return TMVVMViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolderBase(holder: TMVVMViewHolder?, position: Int) {
        val menu = list[position]
        val binding = holder?.binding as ItemSceneBarLayoutBinding
        binding.clContainer.setOnClickListener {
            val previousIndex = list.indexOfFirst { it.isSelected }
            val currentIndex = list.indexOfFirst { it.type == menu.type }
            if (previousIndex != currentIndex) {
                for (item in list) {
                    item.isSelected = item.type == menu.type
                }
                notifyItemChanged(previousIndex)
                notifyItemChanged(currentIndex)
            }
            listener.onClick(menu)
        }
        binding.setVariable(BR.menu, menu)
        binding.executePendingBindings()
    }
}