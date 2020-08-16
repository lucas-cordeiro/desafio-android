package com.picpay.desafio.android.view.main.adapter

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.helper.visible
import com.picpay.desafio.android.model.data.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        Log.d("BUG", "bind: ${user.id}")
        itemView.name.text = user.name?:"Anonímo"
        itemView.username.text = user.username?:"Anonímo"
        Log.d("BUG", "bind: ${user.id} picasso")
        if(user.img.isNullOrBlank()){
            itemView.progressBar.visible = false
            itemView.picture.setImageResource(R.drawable.ic_round_account_circle)
        }else {
            itemView.progressBar.visible = true
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visible = false
                    }

                    override fun onError(e: Exception?) {
                        itemView.progressBar.visible = false
                    }
                })
        }
    }
}