package com.nalan.discoveryspace.data.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalan.discoveryspace.R
import com.nalan.discoveryspace.data.data.model.Status
import kotlinx.android.synthetic.main.item_list_footer.view.*

class LoadingStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: Status?) {
        itemView.progress_bar.visibility = if (status == Status.LOADING) VISIBLE else View.INVISIBLE
        itemView.txt_error.visibility = if (status == Status.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): LoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return LoadingStateViewHolder(view)
        }
    }
}