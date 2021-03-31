package com.dicoding.androidprogramming.submission2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.androidprogramming.submission2.databinding.ItemRowUsersBinding

lateinit var mcontext: Context

class ListUserAdapter(private val listUser: ArrayList<User>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        mcontext = viewGroup.context
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListViewHolder(private val binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            with(binding){
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(120,120))
                    .into(imgAvatar)
                tvName.text = user.name
                tvUsername.text = user.username
                tvRepository.text = user.repository
                tvFollowers.text = user.followers
                tvFollowing.text = user.following

                itemView.setOnClickListener {
                    val userData = User(
                        user.name, user.username, user.avatar, user.company, user.location,
                        user.repository, user.followers, user.following
                    )
                    val intentDetail = Intent(mcontext, UserDetails::class.java)
                    intentDetail.putExtra(UserDetails.EXTRA_DATA, userData)
                    mcontext.startActivity(intentDetail)
                }

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

}