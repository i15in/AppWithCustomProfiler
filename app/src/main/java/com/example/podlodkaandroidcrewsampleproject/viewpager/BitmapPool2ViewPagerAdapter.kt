package com.example.podlodkaandroidcrewsampleproject.viewpager

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podlodkaandroidcrewsampleproject.R
import com.example.podlodkaandroidcrewsampleproject.viewpager.Config.IMAGE_COUNT
import com.example.podlodkaandroidcrewsampleproject.viewpager.Config.ITEM_COUNT

class BitmapPool2ViewPagerAdapter : RecyclerView.Adapter<ImageViewHolder>() {
	
	private val bitmapPool = arrayOfNulls<Bitmap>(IMAGE_COUNT)
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
		return ImageViewHolder(
			LayoutInflater.from(parent.context)
				.inflate(R.layout.layout_image, parent, false)
		)
	}
	
	override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
		holder.bind(position % IMAGE_COUNT, bitmapPool)
	}
	
	override fun getItemCount() = ITEM_COUNT
}