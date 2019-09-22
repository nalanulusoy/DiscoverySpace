package com.nalan.discoveryspace.data.view

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nalan.discoveryspace.data.data.model.Space
import com.nalan.discoveryspace.data.data.model.Status
import com.nalan.discoveryspace.data.view.viewholder.LoadingStateViewHolder
import com.nalan.discoveryspace.data.view.viewholder.MarsPhotosViewHolder

class MarsPhotosAdapter (private val retry: () -> Unit)
    : PagedListAdapter<Space.Photos, RecyclerView.ViewHolder>(NewsDiffCallback) {

    private val DATA_VIEW_TYPE = 1
    private val LOADİNG_VIEW_TYPE = 2

    private var state = Status.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) MarsPhotosViewHolder.create(parent)
        else LoadingStateViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as MarsPhotosViewHolder).bind(getItem(position))
        else if(getItemViewType(position) == LOADİNG_VIEW_TYPE)
            (holder as LoadingStateViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else LOADİNG_VIEW_TYPE
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Space.Photos>() {
            override fun areItemsTheSame(oldItem: Space.Photos, newItem: Space.Photos): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(oldItem: Space.Photos, newItem: Space.Photos): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == Status.LOADING || state == Status.ERROR)
    }

    fun setState(state: Status) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}