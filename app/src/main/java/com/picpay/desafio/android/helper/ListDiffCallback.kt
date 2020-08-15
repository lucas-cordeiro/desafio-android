package com.picpay.desafio.android.helper

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.model.base.ModelBase

class ListDiffCallback (
    private val oldList: List<ModelBase>,
    private val newList: List<ModelBase>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].diff()==newList[newItemPosition].diff()
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}