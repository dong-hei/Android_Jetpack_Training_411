package com.dk.pagingv4

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dk.pagingv4.data.GithubItems

class GithubAdapter : PagingDataAdapter<GithubItems, GithubViewHolder>(DIFF_CALLBACK){

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubItems>() {
            override fun areItemsTheSame(oldItem: GithubItems, newItem: GithubItems): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubItems, newItem: GithubItems): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val items = getItem(position)
        if (items != null) {
            holder.bind(items)
        }
    }
}