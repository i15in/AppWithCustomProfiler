package com.example.podlodkaandroidcrewsampleproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.podlodkaandroidcrewsampleproject.metrics.AppMetricUsageManager
import com.example.podlodkaandroidcrewsampleproject.viewpager.BitmapPool1ViewPagerAdapter

class MainActivity : AppCompatActivity() {
	
	private lateinit var appMetricUsageManager: AppMetricUsageManager
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val viewPager = findViewById<ViewPager2>(R.id.viewPager2)
		viewPager.adapter = BitmapPool1ViewPagerAdapter()
//		viewPager.adapter = BitmapPool2ViewPagerAdapter()
		appMetricUsageManager = AppMetricUsageManager(this)
	}
	
	override fun onResume() {
		super.onResume()
		appMetricUsageManager.startCollect()
	}
	
	override fun onPause() {
		appMetricUsageManager.stopCollect()
		super.onPause()
	}
}