package com.nalan.discoveryspace.data.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE
import com.bumptech.glide.request.RequestOptions



import com.nalan.discoveryspace.data.data.model.Space

import kotlinx.android.synthetic.main.item_space.view.*
import com.bumptech.glide.load.engine.DiskCacheStrategy

import android.content.Context

import android.widget.ImageView
import com.bumptech.glide.Priority
import com.nalan.discoveryspace.R


class MarsPhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(space: Space.Photos?) {
        if (space != null) {
            itemView.tv_info_img.text = space.getEarth_date()
            if (space.getRover() != null) {
                itemView.tv_rover_name.text = space.getRover()!!.getName()
            }


            space.getImg_src()?.let { ImageLoader(it) }?.let {
                loadImageWithGlide(itemView.img_photos_mars.context,
                    it,itemView.img_photos_mars)
            }

        }
    }



    fun loadImageWithGlide(context: Context,imageLoadUrl:String,imageView:ImageView){
        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.error_image)
            .error(R.drawable.error_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(context).load(imageLoadUrl)
            .apply(options)
            .into(imageView)

    }


    fun ImageLoader(imagePath: String): String {

        if (imagePath.startsWith("http://")) {

            val newImagePath = imagePath.replace("http://", "https://")
            return newImagePath
        }

        return imagePath

    }


    companion object {
        fun create(parent: ViewGroup): MarsPhotosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_space, parent, false)
            return MarsPhotosViewHolder(view)
        }
    }
}