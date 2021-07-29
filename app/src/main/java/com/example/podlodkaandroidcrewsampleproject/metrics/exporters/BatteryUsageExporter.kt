package com.example.podlodkaandroidcrewsampleproject.metrics.exporters

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.podlodkaandroidcrewsampleproject.metrics.AppMetricExporter
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class BatteryUsageExporter(private val context: Context) : AppMetricExporter {
	
	private companion object {
		
		const val BATTERY_USAGE_FILENAME = "battery_usage.txt"
		const val INVALIDATE_VALUE = -1
	}
	
	private val batteryPw = PrintWriter(FileOutputStream(File(context.filesDir, BATTERY_USAGE_FILENAME), true), true)
	private val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
	
	override fun export() {
		val batteryStatus: Intent? = context.registerReceiver(null, intentFilter)
		val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, INVALIDATE_VALUE) ?: INVALIDATE_VALUE
		if (status != INVALIDATE_VALUE) {
			batteryPw.println("$status")
		}
	}
	
	override fun close() {
		batteryPw.close()
	}
}