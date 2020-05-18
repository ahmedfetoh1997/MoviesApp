package com.example.moviesapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R

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
                imageUrlWithResize = "0"
            }
            Glide.with(imageView.context)
                .load(imageUrlWithResize)
                .apply(RequestOptions().override(imageWidth, imageHeight))
//            .placeholder(R.drawable.image_loading)
//            .error(R.drawable.img_no_image)
                .into(imageView)
        }


        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImageFromUrl(
            imageView: ImageView,
            imageUrl: String = ""
        ) {
            var imageUrl = imageUrl
            if (imageUrl == null || imageUrl.length == 0) {
                imageUrl = "0"
            }
            Glide.with(imageView.context)
                .load(imageUrl)
            .placeholder(R.color.colorPrimaryDark)
            .error(R.color.colorAccent)
                .into(imageView)
        }
    }

}