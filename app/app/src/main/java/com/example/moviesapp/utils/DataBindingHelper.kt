package com.example.moviesapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.moviesapp.R
import com.squareup.picasso.Picasso

class DataBindingHelper {
    companion object {
        @BindingAdapter(
            value = ["app:imageUrlWithResize", "app:imageWidth", "app:imageHeight"],
            requireAll = true
        )
        @JvmStatic
        fun loadImageWithResize(
            imageView: ImageView,
            imageUrlWithResize: String?,
            imageWidth: Int,
            imageHeight: Int
        ) {
            var imageUrlWithResize = imageUrlWithResize
            if (imageUrlWithResize == null || imageUrlWithResize.length == 0) {
                imageUrlWithResize = "0000"
            }
            Picasso.get()
                .load(imageUrlWithResize)
                .resize(imageWidth, imageHeight)
                .centerCrop()
                .into(imageView)
        }


        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImageFromUrl(
            imageView: ImageView,
            imageUrl: String = ""
        ) {
            var imageUrl = imageUrl
            if (imageUrl.length == 0) {
                imageUrl = "0000"
            }
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.image_not_found)
                .into(imageView)

        }
    }

}