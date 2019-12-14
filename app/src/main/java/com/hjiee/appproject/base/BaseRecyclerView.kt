package com.hjiee.appproject.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyden.util.ItemClickListener
import com.hyden.util.RecyclerDiffUtil
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.recycler_index_item.view.*

class BaseRecyclerView {
    abstract class Adapter<ITEM : Any, B : ViewDataBinding, T>(
        private val layoutId: Int,
        private val bindingVariableId: Int? = 0,
        private val event : ItemClickListener? = null
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        var itemClick : ((T) -> Unit)? = null
        private var list = listOf<ITEM>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
            val holder = object : ViewHolder<B>(
                layoutId,
                parent,
                bindingVariableId
            ) {}
            holder.itemView.setOnClickListener {
                event?.onItemClick(list[holder.adapterPosition] as T,holder.itemView.iv_thumbnail_index)
            }
            return holder
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) =
            holder.onBind(list[position])


        fun updateItems(items : List<ITEM>) {
            RecyclerDiffUtil(list,items).apply {
                list = items
                DiffUtil.calculateDiff(this).let {
                    it.dispatchUpdatesTo(this@Adapter)
                }
            }
        }
    }

    abstract class ViewHolder<B : ViewDataBinding>(
        private val layoutId: Int,
        private val parent: ViewGroup,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

        private val binding = DataBindingUtil.bind<B>(itemView)


        fun onBind(item: Any?) {
            bindingVariableId?.let {
                binding?.setVariable(it, item)
            }
        }
    }
}