package com.example.podlodkaandroidcrewsampleproject.metrics.exporters

import android.content.Context
import android.net.TrafficStats
import com.example.podlodkaandroidcrewsampleproject.metrics.AppMetricExporter
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class NetworkUsageExporter(context: Context) : AppMetricExporter {
	
	private companion object {
		
		const val NETWORK_USAGE_FILENAME = "network_usage.txt"
	}
	
	private val networkPw = PrintWriter(FileOutputStream(File(context.filesDir, NETWORK_USAGE_FILENAME), true), true)
	
	private var transmittedBytes = 0L
	private var receivedBytes = 0L
	
	override fun export() {
		val tBytes = TrafficStats.getTotalTxBytes()
		val rBytes = TrafficStats.getTotalRxBytes()
		
		if (tBytes.toInt() == TrafficStats.UNSUPPORTED || rBytes.toInt() == TrafficStats.UNSUPPORTED) {
			throw RuntimeException("Device does not support network monitoring")
		} else if (transmittedBytes > 0 && receivedBytes > 0) {
			networkPw.println("${tBytes - transmittedBytes} ${rBytes - receivedBytes}")
		}
		
		transmittedBytes = tBytes
		receivedBytes = rBytes
	}
	
	override fun close() {
		networkPw.close()
	}
}