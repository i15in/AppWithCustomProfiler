package com.example.podlodkaandroidcrewsampleproject.viewpager

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.podlodkaandroidcrewsampleproject.viewpager.Config.IMAGE_COUNT

data class ImageViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
	
	private val context = itemView.context
	private val imageView = itemView as ImageView
	
	fun bind(position: Int, bitmapPool: Array<Bitmap?>) {
		if (bitmapPool[position] == null) {
			val bufferedInputStream = context.assets.open("${position % IMAGE_COUNT}.webp")
				.buffered()
			val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
			bitmapPool[position] = bitmap
		}
		imageView.setImageBitmap(bitmapPool[position])
	}
}