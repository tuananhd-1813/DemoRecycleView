package com.asterisk.tuandao.demorecycleview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class ProfileAdapter(
    private val users: List<User>,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.info_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bindData(user)
        holder.bindClickListener(onClickListener)
    }

    override fun onItemDismiss(position: Int) {
        (users as ArrayList).removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(users, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val imageAvatar: ImageView
        private val textUserName: TextView
        private val textDateOfBirth: TextView
        private val item: View

        init {
            imageAvatar = v.findViewById(R.id.imageAvatar)
            textUserName = v.findViewById(R.id.textUserName)
            textDateOfBirth = v.findViewById(R.id.textDateOfBirth)
            item = v
        }

        fun bindData(user: User) {
            imageAvatar.setImageResource(user.imageId)
            textUserName.text = user.userName
            textDateOfBirth.text = user.dateOfBirth
        }

        fun bindClickListener(onClickListener: (Int) -> Unit) {
            item.setOnClickListener {
                onClickListener(position)
            }
        }
    }
}
